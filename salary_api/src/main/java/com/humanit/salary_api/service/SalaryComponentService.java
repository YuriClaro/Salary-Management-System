package com.humanit.salary_api.service;

import com.humanit.salary_api.dto.SalaryComponentDTO;
import com.humanit.salary_api.exception.salary.SalaryByIdNotFoundException;
import com.humanit.salary_api.exception.salaryComponent.SalaryComponentByIdNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SalaryComponentService {
    SalaryComponentDTO createSalaryComponent(SalaryComponentDTO componentDTO) throws SalaryByIdNotFoundException;
    SalaryComponentDTO getSalaryComponentById(UUID id) throws SalaryComponentByIdNotFoundException;
    SalaryComponentDTO updateSalaryComponent(UUID id, SalaryComponentDTO componentDTO)
            throws SalaryComponentByIdNotFoundException;
    Page<SalaryComponentDTO> getAllSalaryComponents(Pageable pageable);
    Page<SalaryComponentDTO> getAllSalaryComponentBySalaryId(UUID id, Pageable pageable)
            throws SalaryByIdNotFoundException;
    void deleteSalaryComponent(UUID id) throws SalaryComponentByIdNotFoundException;
}
