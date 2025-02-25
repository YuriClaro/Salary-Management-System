package com.humanit.salary_api.service.impl;

import com.humanit.salary_api.dto.EmailDTO;
import com.humanit.salary_api.dto.SalaryDTO;
import com.humanit.salary_api.dto.SalaryDecisionDTO;
import com.humanit.salary_api.enumerator.SalaryStatus;
import com.humanit.salary_api.exception.collaborator.CollaboratorByIdNotFoundException;
import com.humanit.salary_api.exception.collaborator.InvalidCollaboratorException;
import com.humanit.salary_api.exception.salary.InvalidSalaryDecisionException;
import com.humanit.salary_api.exception.salary.SalaryByIdNotFoundException;
import com.humanit.salary_api.mapper.SalaryMapper;
import com.humanit.salary_api.model.Collaborator;
import com.humanit.salary_api.model.Salary;
import com.humanit.salary_api.repository.CollaboratorRepository;
import com.humanit.salary_api.repository.SalaryRepository;
import com.humanit.salary_api.service.SalaryService;
import com.humanit.salary_api.specification.SalarySpecification;
import com.humanit.salary_api.validator.SalaryValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.humanit.salary_api.validator.CollaboratorValidator.*;
import static com.humanit.salary_api.validator.SalaryValidator.findSalaryById;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {
    private final SalaryRepository salaryRepository;
    private final SalaryMapper salaryMapper;
    private final CollaboratorRepository collaboratorRepository;

    @Override
    @Transactional
    public SalaryDTO createSalary(SalaryDTO salaryDTO) throws CollaboratorByIdNotFoundException {
        Collaborator collaborator = findCollaboratorById(salaryDTO.getCollaboratorId(), collaboratorRepository);
        log.info("Creating salary for collaborator ID: {}", salaryDTO.getCollaboratorId());
        Salary salary = salaryMapper.toEntity(salaryDTO);
        salary.setCollaborator(collaborator);
        Salary savedSalary = salaryRepository.save(salary);
        log.info("Salary was created with ID {}, for collaborator: {}",
                savedSalary.getId(), savedSalary.getCollaborator().getId());
        return salaryMapper.toDTO(savedSalary);
    }

    @Override
    public SalaryDTO getSalaryById(UUID id) throws SalaryByIdNotFoundException {
        log.info("Fetching salary with ID: {}", id);
        Salary salary = findSalaryById(id, salaryRepository);
        log.info("Salary with ID: {} returned successfully.", id);
        return salaryMapper.toDTO(salary);
    }

    @Override
    @Transactional
    public SalaryDTO updateSalary(UUID id, SalaryDTO salaryDTO) throws SalaryByIdNotFoundException {
        log.info("Attempting to update salary ID: {}", id);
        Salary existingSalary = findSalaryById(id, salaryRepository);
        salaryMapper.updateEntityFromDTO(salaryDTO, existingSalary);
        Salary updatedSalary = salaryRepository.save(existingSalary);
        log.info("Salary with ID: {} has been updated.", id);
        return salaryMapper.toDTO(updatedSalary);
    }

    @Override
    public Page<SalaryDTO> getAllSalaries(Pageable pageable) {
        log.info("Fetching all salaries with pagination - Page: {}, Size: {}",
                pageable.getPageNumber(), pageable.getPageSize());
        Page<Salary> salaryPage = salaryRepository.findAll(pageable);
        log.info("Total salary found: {}, Total pages: {}",
                salaryPage.getTotalElements(), salaryPage.getTotalPages());
        return salaryPage.map(salaryMapper::toDTO);
    }

    @Override
    public Page<SalaryDTO> getAllSalariesByCollaboratorId(
            UUID id, Pageable pageable) throws CollaboratorByIdNotFoundException {
        log.info("Fetching all salaries for collaborator ID {} with pagination - Page: {}, Size: {}",
                id ,pageable.getPageNumber(), pageable.getPageSize());
        findCollaboratorById(id, collaboratorRepository);
        Page<Salary> salaryPage = salaryRepository.findAllByCollaboratorId(id, pageable);
        log.info("Total salary components found: {}, Total pages: {}",
                salaryPage.getTotalElements(), salaryPage.getTotalPages());
        return salaryPage.map(salaryMapper::toDTO);
    }

    @Override
    @Transactional
    public void deleteSalary(UUID id) throws SalaryByIdNotFoundException {
        log.info("Attempting to delete user ID: {}", id);
        SalaryValidator.findSalaryById(id, salaryRepository);
        salaryRepository.deleteById(id);
        log.info("Salary ID: {} deleted successfully", id);
    }

    @Override
    public Page<SalaryDTO> getAllSalariesByStatusBetween(
            List<SalaryStatus> status, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        log.info("Fetching all salaries with specification with pagination - Page: {}, Size: {}",
                pageable.getPageNumber(), pageable.getPageSize());
        SalarySpecification specification = new SalarySpecification(status, startDate, endDate);
        Page<Salary> salaryPage = salaryRepository.findAll(specification, pageable);
        log.info("Total salary found: {}, Total pages: {}",
                salaryPage.getTotalElements(), salaryPage.getTotalPages());
        return  salaryPage.map(salaryMapper::toDTO);
    }

    @Override
    @Transactional
    public SalaryDTO acceptSalaryProposed(UUID id, SalaryDecisionDTO salaryDecisionDTO)
            throws SalaryByIdNotFoundException, InvalidSalaryDecisionException, InvalidCollaboratorException {
        log.info("Attempting to process salary decision for id: {}", id);

        Collaborator collaborator = findCollaboratorByEmail(salaryDecisionDTO.getEmail(), collaboratorRepository);
        Salary proposedSalary = findSalaryById(id, salaryRepository);

        Salary currentSalary = salaryRepository.findByStatusAndCollaboratorId(SalaryStatus.CURRENT, collaborator.getId());

        SalaryStatus decision = salaryDecisionDTO.getDecision();

        switch (decision) {
            case ACCEPT -> {
                currentSalary.setStatus(SalaryStatus.ARCHIVED);
                salaryRepository.save(currentSalary);

                proposedSalary.setStatus(SalaryStatus.CURRENT);
                salaryRepository.save(proposedSalary);

                log.info("Salary proposal accepted and updated to CURRENT status.");
                return salaryMapper.toDTO(proposedSalary);
            }
            case REJECT -> {
                proposedSalary.setStatus(SalaryStatus.ARCHIVED);
                salaryRepository.save(proposedSalary);

                log.info("Salary proposal rejected and archived.");
                return salaryMapper.toDTO(currentSalary);
            }
            default -> throw new InvalidSalaryDecisionException();
        }
    }

    @Override
    public Page<SalaryDTO> getOwnSalariesByCollaboratorId(Pageable pageable, EmailDTO emailDTO) {
        Optional<Collaborator> collaborator = collaboratorRepository.findByEmail(emailDTO.getReceiver());
        log.info("Fetching all salaries for collaborator ID {} with pagination - Page: {}, Size: {}",
                collaborator.get().getId() ,pageable.getPageNumber(), pageable.getPageSize());
        Page<Salary> salaryPage = salaryRepository.findAllByCollaboratorId(collaborator.get().getId(), pageable);
        log.info("Total salary components found: {}, Total pages: {}",
                salaryPage.getTotalElements(), salaryPage.getTotalPages());
        return salaryPage.map(salaryMapper::toDTO);
    }
}
