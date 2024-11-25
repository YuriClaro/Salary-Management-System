package com.humanit.portal_api.service;

import com.humanit.portal_api.dto.salaries.SalaryDTO;
import com.humanit.portal_api.enumerator.SalaryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface SalaryService {
    SalaryDTO createSalary(SalaryDTO salaryDTO);
    SalaryDTO getSalaryById(UUID id);
    Page<SalaryDTO> getAllSalaries(Pageable pageable);
    Page<SalaryDTO> getAllSalariesByCollaboratorId(UUID id, Pageable pageable);
    Page<SalaryDTO> getAllSalariesByStatusBetween(
            List<SalaryStatus> status, LocalDate startDate, LocalDate endDate, Pageable pageable);
    SalaryDTO updateSalary(UUID id, SalaryDTO salaryDTO);
    void deleteSalary(UUID id);
}
