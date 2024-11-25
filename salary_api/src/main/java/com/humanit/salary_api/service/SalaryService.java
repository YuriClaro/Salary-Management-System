package com.humanit.salary_api.service;

import com.humanit.salary_api.dto.SalaryDTO;
import com.humanit.salary_api.enumerator.SalaryStatus;
import com.humanit.salary_api.exception.collaborator.CollaboratorByIdNotFoundException;
import com.humanit.salary_api.exception.salary.SalaryByIdNotFoundException;
import com.humanit.salary_api.specification.SalarySpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface SalaryService {
    SalaryDTO createSalary(SalaryDTO salaryDTO) throws CollaboratorByIdNotFoundException;
    SalaryDTO getSalaryById(UUID id) throws SalaryByIdNotFoundException;
    SalaryDTO updateSalary(UUID id, SalaryDTO salaryDTO) throws SalaryByIdNotFoundException;
    Page<SalaryDTO> getAllSalaries(Pageable pageable);
    Page<SalaryDTO> getAllSalariesByStatusBetween(
            List<SalaryStatus> statusList, LocalDate startDate, LocalDate endDate, Pageable pageable);
    Page<SalaryDTO> getAllSalariesByCollaboratorId(UUID id, Pageable pageable) throws CollaboratorByIdNotFoundException;
    void deleteSalary(UUID id) throws SalaryByIdNotFoundException;
}
