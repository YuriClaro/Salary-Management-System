package com.humanit.portal_api.service.impl;

import com.humanit.portal_api.dto.export.*;
import com.humanit.portal_api.jwt.JwtService;
import com.humanit.portal_api.service.ExportService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExportServiceImpl implements ExportService {
    private final JwtService jwtService;
    private final KafkaTemplate<String, EmailDTO> kafkaEmailTemplate;
    private final KafkaTemplate<String, EmailWithCollaboratorIdDTO> kafkaEmailWithCollaboratorIdTemplate;
    private final KafkaTemplate<String, DatesRequestDTO> kafkaExportAllSalariesBetweenDatesTemplate;
    private final KafkaTemplate<String, EmailWithStatusDTO> kafkaExportAllSalariesByStatusTemplate;

    @Value("${spring.kafka.topic.all_salary}")
    private String allSalaryTopic;

    @Value("${spring.kafka.topic.salary_collaborator_id}")
    private String salaryByCollaboratorIdTopic;

    @Value("${spring.kafka.topic.all_salaries_between_dates}")
    private String allSalariesBetweenDates;

    @Value("${spring.kafka.topic.all_salaries_status}")
    private String allSalariesByStatus;

    @Value("${spring.kafka.topic.all_own_salaries}")
    private String allOwnSalariesTopic;

    @Override
    public EmailResponseDTO exportAllSalaries(HttpServletRequest request) {
        log.info("Starting the process to export all salaries...");
        String authHeader = request.getHeader("Authorization");
        EmailDTO emailDTO = new EmailDTO(jwtService.extractEmail(authHeader.substring(7)));
        kafkaEmailTemplate.send(allSalaryTopic, emailDTO);
        log.info("Sending request for export all salaries to {}", emailDTO.getReceiver());
        return new EmailResponseDTO("Request sent successfully to export all salaries",
                emailDTO.getReceiver());
    }

    @Override
    public EmailResponseDTO exportSalaryByCollaboratorId(HttpServletRequest request, UUID id) {
        log.info("Starting the process to export salary by collaborator ID: {}", id);
        String authHeader = request.getHeader("Authorization");
        EmailWithCollaboratorIdDTO emailWithCollaboratorIdDTO = new EmailWithCollaboratorIdDTO(
                jwtService.extractEmail(authHeader.substring(7)),
                id);
        kafkaEmailWithCollaboratorIdTemplate.send(salaryByCollaboratorIdTopic, emailWithCollaboratorIdDTO);
        return new EmailResponseDTO("Request sent successfully to export salary about collaborator " + id,
                emailWithCollaboratorIdDTO.getReceiver());
    }

    @Override
    public EmailResponseDTO exportAllSalariesBetweenDates(HttpServletRequest request, DatesDTO datesDTO) {
        log.info("Starting the process to export all salaries between {} to {}.",
                datesDTO.getInitDate(), datesDTO.getEndDate());
        String authHeader = request.getHeader("Authorization");
        DatesRequestDTO emailRequestDTO = new DatesRequestDTO(
                jwtService.extractEmail(authHeader.substring(7)),
                datesDTO.getInitDate(),
                datesDTO.getEndDate());
        kafkaExportAllSalariesBetweenDatesTemplate.send(allSalariesBetweenDates, emailRequestDTO);
        return new EmailResponseDTO("Request sent successfully to export all salaries between dates: "
                + emailRequestDTO.getInitDate() + " to " + emailRequestDTO.getEndDate(), emailRequestDTO.getReceiver());
    }

    @Override
    public EmailResponseDTO exportAllSalariesByStatus(HttpServletRequest request, SalaryStatusDTO statusDTO) {
        log.info("Starting the process to export all salaries by status: {}", statusDTO.getStatus());
        String authHeader = request.getHeader("Authorization");
        EmailWithStatusDTO emailWithStatusDTO = new EmailWithStatusDTO(
                jwtService.extractEmail(authHeader.substring(7)), statusDTO.getStatus());
        kafkaExportAllSalariesByStatusTemplate.send(allSalariesByStatus, emailWithStatusDTO);
        return new EmailResponseDTO(
                "Request sent successfully to export all salaries by status.", emailWithStatusDTO.getReceiver());
    }

    @Override
    public EmailResponseDTO exportAllOwnSalaries(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        UUID userId = jwtService.extractId(authHeader.substring(7));
        log.info("Starting the process to export all salaries by user ID: {}", userId);
        EmailDTO emailDTO = new EmailDTO(jwtService.extractEmail(authHeader.substring(7)));
        kafkaEmailTemplate.send(allOwnSalariesTopic, emailDTO);
        return new EmailResponseDTO("Request sent successfully to export salary about collaborator " + userId,
                emailDTO.getReceiver());
    }
}
