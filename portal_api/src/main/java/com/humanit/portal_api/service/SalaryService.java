package com.humanit.portal_api.service;

import com.humanit.portal_api.dto.salaries.SalaryDTO;
import com.humanit.portal_api.dto.salaries.SalaryDecisionDTO;
import com.humanit.portal_api.enumerator.SalaryStatus;
import com.humanit.portal_api.exceptions.salaryApi.InvalidSalaryDecisionException;
import com.humanit.portal_api.exceptions.salaryApi.SalaryByIdNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface SalaryService {
    SalaryDTO createSalary(SalaryDTO salaryDTO);
    SalaryDTO getSalaryById(UUID id);
    SalaryDTO acceptSalaryProposed(UUID id, SalaryDecisionDTO salaryDecisionDTO, HttpServletRequest request)
            throws InvalidSalaryDecisionException, SalaryByIdNotFoundException;
    Page<SalaryDTO> getAllSalaries(Pageable pageable);
    Page<SalaryDTO> getAllSalariesByCollaboratorId(UUID id, Pageable pageable);
    Page<SalaryDTO> getAllOwnSalaries(Pageable pageable, HttpServletRequest request);
    Page<SalaryDTO> getAllSalariesByStatusBetween(
            List<SalaryStatus> status, LocalDate startDate, LocalDate endDate, Pageable pageable);
    SalaryDTO updateSalary(UUID id, SalaryDTO salaryDTO);
    void deleteSalary(UUID id);


}
