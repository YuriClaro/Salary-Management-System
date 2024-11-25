package com.humanit.portal_api.service.impl;

import com.humanit.portal_api.client.SalaryFeignClient;
import com.humanit.portal_api.dto.salaries.SalaryDTO;
import com.humanit.portal_api.enumerator.SalaryStatus;
import com.humanit.portal_api.service.SalaryService;
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
        return salaries;    }

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
}
