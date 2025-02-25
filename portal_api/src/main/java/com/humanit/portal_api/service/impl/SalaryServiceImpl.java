package com.humanit.portal_api.service.impl;

import com.humanit.portal_api.client.SalaryFeignClient;
import com.humanit.portal_api.dto.export.EmailDTO;
import com.humanit.portal_api.dto.salaries.SalaryDTO;
import com.humanit.portal_api.dto.salaries.SalaryDecisionDTO;
import com.humanit.portal_api.enumerator.SalaryStatus;
import com.humanit.portal_api.exceptions.salaryApi.InvalidSalaryDecisionException;
import com.humanit.portal_api.exceptions.salaryApi.SalaryByIdNotFoundException;
import com.humanit.portal_api.jwt.JwtService;
import com.humanit.portal_api.service.SalaryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {
    private final SalaryFeignClient salaryFeignClient;
    private final JwtService jwtService;

    @Override
    public SalaryDTO createSalary(SalaryDTO salaryDTO) {
        log.info("Starting creation of Salary with details: {}", salaryDTO);
        SalaryDTO createdSalary = salaryFeignClient.createSalary(salaryDTO).getBody();
        log.info("Salary created successfully with ID: {}", createdSalary.getId());
        return createdSalary;
    }

    @Override
    public Page<SalaryDTO> getAllSalaries(Pageable pageable) {
        log.info("Retrieving all Salaries with pagination: Page {}, Size {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<SalaryDTO> salaries = salaryFeignClient.getAllSalaries(pageable).getBody();
        log.info("Successfully retrieved {} Salaries", salaries.getTotalElements());
        return salaries;
    }

    @Override
    public SalaryDTO getSalaryById(UUID id) {
        log.info("Retrieving Salary with ID: {}", id);
        SalaryDTO salaryDTO = salaryFeignClient.getSalaryById(id).getBody();
        log.info("Successfully retrieved Salary with ID: {}", salaryDTO.getId());
        return salaryDTO;    }

    @Override
    public Page<SalaryDTO> getAllSalariesByCollaboratorId(UUID id, Pageable pageable) {
        log.info("Retrieving all Salaries for Collaborator ID: {} with pagination: Page {}, Size {}", id,
                pageable.getPageNumber(), pageable.getPageSize());
        Page<SalaryDTO> salaries = salaryFeignClient.getAllSalariesByCollaboratorId(id, pageable).getBody();
        log.info("Successfully retrieved {} Salaries for Collaborator ID: {}", salaries.getTotalElements(), id);
        return salaries;
    }

    @Override
    public SalaryDTO updateSalary(UUID id, SalaryDTO salaryDTO) {
        log.info("Updating Salary with ID: {} with details: {}", id, salaryDTO);
        SalaryDTO updatedSalary = salaryFeignClient.updateSalary(id, salaryDTO).getBody();
        log.info("Successfully updated Salary with ID: {}", updatedSalary.getId());
        return updatedSalary;
    }

    @Override
    public void deleteSalary(UUID id) {
        log.info("Deleting Salary with ID: {}", id);
        salaryFeignClient.deleteSalary(id);
        log.info("Successfully deleted Salary with ID: {}", id);
    }

    @Override
    public Page<SalaryDTO> getAllSalariesByStatusBetween(
            List<SalaryStatus> status, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        log.info("Retrieving all salaries by specifications");
        return salaryFeignClient.getAllSalariesByStatusBetween(status, startDate, endDate, pageable).getBody();
    }

    @Override
    public SalaryDTO acceptSalaryProposed(UUID id, SalaryDecisionDTO salaryDecisionDTO, HttpServletRequest request)
            throws InvalidSalaryDecisionException, SalaryByIdNotFoundException {
        log.info("Starting salary decision process for salary ID: {} with decision: {}",
                id, salaryDecisionDTO.getDecision());

        salaryDecisionDTO.setEmail(jwtService.extractEmail(request.getHeader("Authorization").substring(7)));
        SalaryDTO proposedSalary = salaryFeignClient.acceptSalaryProposed(id, salaryDecisionDTO).getBody();
        log.info("Successfully processed salary decision for salary ID: {}. New status: {}",
                id, proposedSalary.getStatus());
        return proposedSalary;
    }

    @Override
    public Page<SalaryDTO> getAllOwnSalaries(Pageable pageable, HttpServletRequest request) {
        UUID userId = jwtService.extractId(request.getHeader("Authorization").substring(7));
        log.info("Retrieving all Salaries for User Credentials ID: {} with pagination: Page {}, Size {}", userId,
                pageable.getPageNumber(), pageable.getPageSize());

        EmailDTO email = new EmailDTO(jwtService.extractEmail(request.getHeader("Authorization").substring(7)));

        Page<SalaryDTO> salaries = salaryFeignClient.getAllOwnSalaries(pageable, email).getBody();
        log.info("Successfully retrieved {} Salaries for User Credentials ID: {}", salaries.getTotalElements(), userId);
        return salaries;
    }
}
