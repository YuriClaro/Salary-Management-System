package com.humanit.portal_api.service.impl;

import com.humanit.portal_api.client.SalaryComponentsFeignClient;
import com.humanit.portal_api.dto.salaries.SalaryComponentDTO;
import com.humanit.portal_api.service.SalaryComponentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalaryComponentsServiceImpl implements SalaryComponentsService {
    private final SalaryComponentsFeignClient componentsFeignClient;

    @Override
    public SalaryComponentDTO createSalaryComponent(SalaryComponentDTO componentDTO) {
        log.info("Starting creation of Salary Component");
        SalaryComponentDTO existingComponentDTO = componentsFeignClient.createSalaryComponent(componentDTO).getBody();
        log.info("Salary Component created successfully: {}", existingComponentDTO.getId());
        return componentDTO;
    }

    @Override
    public Page<SalaryComponentDTO> getAllSalariesComponents(Pageable pageable) {
        log.info("Retrieving all Salary Components with pagination: Page {}, Size {}", pageable.getPageNumber(),
                pageable.getPageSize());
        Page<SalaryComponentDTO> components = componentsFeignClient.getAllSalariesComponents(pageable).getBody();
        log.info("Successfully retrieved {} Salary Components", components.getTotalElements());
        return components;
    }

    @Override
    public SalaryComponentDTO getSalaryComponentById(UUID id) {
        log.info("Retrieving Salary Component with ID: {}", id);
        SalaryComponentDTO componentDTO = componentsFeignClient.getSalaryComponentById(id).getBody();
        log.info("Successfully retrieved Salary Component with ID: {}", componentDTO.getId());
        return componentDTO;
    }

    @Override
    public Page<SalaryComponentDTO> getAllSalaryComponentBySalaryId(UUID id, Pageable pageable) {
        log.info("Retrieving all Salary Components for Salary ID: {} with pagination: Page {}, Size {}",
                id, pageable.getPageNumber(), pageable.getPageSize());
        Page<SalaryComponentDTO> components =
                componentsFeignClient.getAllSalaryComponentBySalaryId(id, pageable).getBody();
        log.info("Successfully retrieved {} Salary Components for Salary ID: {}", components.getTotalElements(), id);
        return components;
    }

    @Override
    public SalaryComponentDTO updateSalaryComponent(UUID id, SalaryComponentDTO componentDTO) {
        log.info("Updating Salary Component with ID: {}", id);
        SalaryComponentDTO updatedComponent = componentsFeignClient.updateSalaryComponent(id, componentDTO).getBody();
        log.info("Successfully updated Salary Component with ID: {}", updatedComponent.getId());
        return updatedComponent;
    }

    @Override
    public void deleteSalaryComponent(UUID id) {
        log.info("Deleting Salary Component with ID: {}", id);
        componentsFeignClient.deleteSalaryComponent(id);
        log.info("Successfully deleted Salary Component with ID: {}", id);
    }
}
