package com.humanit.salary_api.service;

import com.humanit.salary_api.dto.EmailDTO;
import com.humanit.salary_api.dto.SalaryDTO;
import com.humanit.salary_api.dto.SalaryDecisionDTO;
import com.humanit.salary_api.enumerator.SalaryStatus;
import com.humanit.salary_api.exception.collaborator.CollaboratorByIdNotFoundException;
import com.humanit.salary_api.exception.collaborator.InvalidCollaboratorException;
import com.humanit.salary_api.exception.salary.InvalidSalaryDecisionException;
import com.humanit.salary_api.exception.salary.SalaryByIdNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface SalaryService {
    SalaryDTO createSalary(SalaryDTO salaryDTO) throws CollaboratorByIdNotFoundException;
    SalaryDTO getSalaryById(UUID id) throws SalaryByIdNotFoundException;
    SalaryDTO updateSalary(UUID id, SalaryDTO salaryDTO) throws SalaryByIdNotFoundException;
    SalaryDTO acceptSalaryProposed(UUID id, SalaryDecisionDTO salaryDecisionDTO)
            throws SalaryByIdNotFoundException, InvalidSalaryDecisionException, InvalidCollaboratorException,
            CollaboratorByIdNotFoundException;
    Page<SalaryDTO> getAllSalaries(Pageable pageable);
    Page<SalaryDTO> getAllSalariesByStatusBetween(
            List<SalaryStatus> statusList, LocalDate startDate, LocalDate endDate, Pageable pageable);
    Page<SalaryDTO> getAllSalariesByCollaboratorId(UUID id, Pageable pageable) throws CollaboratorByIdNotFoundException;
    Page<SalaryDTO> getOwnSalariesByCollaboratorId(Pageable pageable, EmailDTO emailDTO)
            throws CollaboratorByIdNotFoundException, InvalidCollaboratorException;
    void deleteSalary(UUID id) throws SalaryByIdNotFoundException;
}
