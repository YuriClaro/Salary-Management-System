package com.humanit.salary_api.service.impl;

import com.humanit.salary_api.dto.SalaryComponentDTO;
import com.humanit.salary_api.exception.salary.SalaryByIdNotFoundException;
import com.humanit.salary_api.exception.salaryComponent.SalaryComponentByIdNotFoundException;
import com.humanit.salary_api.mapper.SalaryComponentMapper;
import com.humanit.salary_api.model.Salary;
import com.humanit.salary_api.model.SalaryComponent;
import com.humanit.salary_api.repository.SalaryComponentRepository;
import com.humanit.salary_api.repository.SalaryRepository;
import com.humanit.salary_api.service.SalaryComponentService;
import com.humanit.salary_api.validator.SalaryComponentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.humanit.salary_api.validator.SalaryComponentValidator.findSalaryComponentById;
import static com.humanit.salary_api.validator.SalaryValidator.findSalaryById;


import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalaryComponentServiceImpl implements SalaryComponentService {
    private final SalaryComponentRepository componentRepository;
    private final SalaryComponentMapper componentMapper;
    private final SalaryRepository salaryRepository;

    @Override
    @Transactional
    public SalaryComponentDTO createSalaryComponent(SalaryComponentDTO componentDTO)
            throws SalaryByIdNotFoundException {
        UUID salaryID = componentDTO.getSalaryId();
        log.info("Creating salary component for salary with ID: {}", salaryID);
        Salary salary = findSalaryById(salaryID, salaryRepository);
        SalaryComponent component = componentMapper.toEntity(componentDTO);
        component.setSalary(salary);
        SalaryComponent savedComponent = componentRepository.save(component);
        log.info("SalarySpecification component created for SalarySpecification with ID: {}, and salary component: {}",
                salaryID, savedComponent.getId());
        return componentMapper.toDTO(savedComponent);
    }

    @Override
    public SalaryComponentDTO getSalaryComponentById(UUID id) throws SalaryComponentByIdNotFoundException {
        log.info("Fetching salary component with ID: {}", id);
        SalaryComponent component = findSalaryComponentById(id, componentRepository);
        log.info("SalarySpecification component ID: {} retrieved successfully.", id);
        return componentMapper.toDTO(component);
    }

    @Override
    @Transactional
    public SalaryComponentDTO updateSalaryComponent(
            UUID id, SalaryComponentDTO componentDTO) throws SalaryComponentByIdNotFoundException {
        log.info("Attempting to update salary component with ID: {}", id);
        SalaryComponent existingComponent = findSalaryComponentById(id, componentRepository);
        componentMapper.updateEntityFromDTO(componentDTO, existingComponent);
        SalaryComponent updatedComponent = componentRepository.save(existingComponent);
        log.info("SalarySpecification component with ID: {} has been updated.", id);
        return componentMapper.toDTO(updatedComponent);
    }

    @Override
    public Page<SalaryComponentDTO> getAllSalaryComponents(Pageable pageable) {
        log.info("Fetching all salary components with pagination - Page: {}, Size: {}",
                pageable.getPageNumber(), pageable.getPageSize());
        Page<SalaryComponent> componentPage = componentRepository.findAll(pageable);
        log.info("Total salary components found: {}, Total pages: {}",
                componentPage.getTotalElements(), componentPage.getTotalPages());
        return componentPage.map(componentMapper::toDTO);
    }

    @Override
    public Page<SalaryComponentDTO> getAllSalaryComponentBySalaryId(
            UUID id, Pageable pageable) throws SalaryByIdNotFoundException {
        log.info("Fetching all salary components with pagination - Page: {}, Size: {}",
                pageable.getPageNumber(), pageable.getPageSize());
        findSalaryById(id, salaryRepository);
        Page<SalaryComponent> componentPage = componentRepository.findAllBySalaryId(id, pageable);
        log.info("Total salary components found: {}, Total pages: {}",
                componentPage.getTotalElements(), componentPage.getTotalPages());
        return componentPage.map(componentMapper::toDTO);
    }

    @Override
    @Transactional
    public void deleteSalaryComponent(UUID id) throws SalaryComponentByIdNotFoundException {
        log.info("Attempting to delete salary component with ID: {}", id);
        SalaryComponentValidator.findSalaryComponentById(id, componentRepository);
        componentRepository.deleteById(id);
        log.info("SalarySpecification component with ID {} deleted successfully", id);
    }
}
