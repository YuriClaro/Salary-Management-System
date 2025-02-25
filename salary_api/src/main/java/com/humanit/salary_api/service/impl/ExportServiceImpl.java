package com.humanit.salary_api.service.impl;

import com.humanit.salary_api.dto.*;
import com.humanit.salary_api.exception.export.SalaryExportException;
import com.humanit.salary_api.model.Collaborator;
import com.humanit.salary_api.repository.CollaboratorRepository;
import com.humanit.salary_api.service.ExcelService;
import com.humanit.salary_api.service.ExportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExportServiceImpl implements ExportService {
    private final KafkaTemplate<String, EmailWithAttachmentDTO> kafkaTemplateExportExcelFile;
    private final ExcelService excelService;
    private final CollaboratorRepository collaboratorRepository;

    @Value("${spring.kafka.topic.email_all_salaries}")
    private String emailAllSalariesTopic;

    @Value("${spring.kafka.topic.email_all_salaries_status}")
    private String emailAllSalariesByStatusTopic;

    @Value("${spring.kafka.topic.email_all_salaries_dates}")
    private String emailAllSalariesBetweenDatesTopic;

    @Value("${spring.kafka.topic.email_salary_collaborator_id}")
    private String emailAllSalariesByCollaboratorIdTopic;

    @Value("${spring.kafka.topic.email_all_own_salaries}")
    private String emailAllOwnSalariesTopic;

    @Override
    @KafkaListener(topics = "${spring.kafka.topic.all_salary}",
            groupId = "${spring.kafka.group.all_salary}",
            containerFactory = "kafkaListenerEmailContainerFactory"
    )
    public void handleExportAllSalaries(EmailDTO emailDTO) throws SalaryExportException {
        log.info("Received all salaries export request for email: {}", emailDTO.getReceiver());
        byte[] excelFile = excelService.exportAllSalaries();
        EmailWithAttachmentDTO emailWithAttachmentDTO = new EmailWithAttachmentDTO(emailDTO.getReceiver(), excelFile);
        kafkaTemplateExportExcelFile.send(emailAllSalariesTopic, emailWithAttachmentDTO);
        log.info("Excel file sent to Kafka topic '{}' for receiver: {}", emailAllSalariesTopic, emailDTO.getReceiver());
    }

    @Override
    @KafkaListener(topics = "${spring.kafka.topic.salary_collaborator_id}",
            groupId = "${spring.kafka.group.salary_collaborator_id}",
            containerFactory = "kafkaListenerEmailWithCollaboratorIdContainerFactory"
    )
    public void handleExportSalaryByCollaboratorId(EmailWithCollaboratorIdDTO emailDTO) throws SalaryExportException {
        log.info("Received salary by collaborator ID: {} export request for email: {}",
                emailDTO.getId(), emailDTO.getReceiver());
        byte[] excelFile = excelService.exportSalaryByCollaboratorId(emailDTO.getId());
        EmailWithAttachmentDTO emailWithAttachmentDTO = new EmailWithAttachmentDTO(emailDTO.getReceiver(), excelFile);
        kafkaTemplateExportExcelFile.send(emailAllSalariesByCollaboratorIdTopic, emailWithAttachmentDTO);
        log.info("Excel file sent to Kafka topic '{}' for receiver: {}",
                emailAllSalariesByCollaboratorIdTopic, emailDTO.getReceiver());
    }

    @Override
    @KafkaListener(topics = "${spring.kafka.topic.all_salaries_between_dates}",
            groupId = "${spring.kafka.group.all_salaries_between_dates}",
            containerFactory = "kafkaListenerAllSalariesBetweenDatesContainerFactory"
    )
    public void handleExportAllSalariesBetweenDates(DatesRequestDTO datesRequestDTO) throws SalaryExportException {
        log.info("Received all salaries between dates {} to {} export request for email: {}",
                datesRequestDTO.getInitDate(), datesRequestDTO.getEndDate(), datesRequestDTO.getReceiver());
        byte[] excelFile = excelService.exportAllSalariesBetweenDates(
                datesRequestDTO.getInitDate(), datesRequestDTO.getEndDate());
        EmailWithAttachmentDTO emailWithAttachmentDTO = new EmailWithAttachmentDTO(
                datesRequestDTO.getReceiver(), excelFile);
        kafkaTemplateExportExcelFile.send(emailAllSalariesBetweenDatesTopic, emailWithAttachmentDTO);
        log.info("Excel file sent to Kafka topic '{}' for receiver: {}",
                emailAllSalariesBetweenDatesTopic, datesRequestDTO.getReceiver());
    }

    @Override
    @KafkaListener(topics = "${spring.kafka.topic.all_salaries_status}",
            groupId = "${spring.kafka.group.all_salaries_status}",
            containerFactory = "kafkaListenerAllSalariesByStatusContainerFactory"
    )
    public void handleExportAllSalariesByStatus(EmailWithStatusDTO emailWithStatusDTO) throws SalaryExportException {
        log.info("Received all salaries by status export for email: {}", emailWithStatusDTO.getReceiver());
        byte[] excelFile = excelService.exportAllSalariesByStatus(emailWithStatusDTO.getStatus());
        EmailWithAttachmentDTO emailWithAttachmentDTO =
                new EmailWithAttachmentDTO(emailWithStatusDTO.getReceiver(), excelFile);
        kafkaTemplateExportExcelFile.send(emailAllSalariesByStatusTopic, emailWithAttachmentDTO);
        log.info("Excel file sent to Kafka topic '{}' for receiver: {}",
                emailAllSalariesByStatusTopic, emailWithStatusDTO.getReceiver());
    }

    @Override
    @KafkaListener(topics = "${spring.kafka.topic.all_own_salaries}",
            groupId = "${spring.kafka.group.all_own_salaries}",
            containerFactory = "kafkaListenerAllOwnSalariesFactory"
    )
    public void handleExportAllOwnSalaries(EmailDTO emailDTO) throws SalaryExportException {
        Optional<Collaborator> collaborator = collaboratorRepository.findByEmail(emailDTO.getReceiver());
        log.info("Received salary by collaborator ID: {} export request for email: {}",
                collaborator.get().getId(), emailDTO.getReceiver());
        byte[] excelFile = excelService.exportSalaryByCollaboratorId(collaborator.get().getId());
        EmailWithAttachmentDTO emailWithAttachmentDTO = new EmailWithAttachmentDTO(emailDTO.getReceiver(), excelFile);
        kafkaTemplateExportExcelFile.send(emailAllOwnSalariesTopic, emailWithAttachmentDTO);
        log.info("Excel file sent to Kafka topic '{}' for receiver: {}",
                emailAllOwnSalariesTopic, emailDTO.getReceiver());
    }

}