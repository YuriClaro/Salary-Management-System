package com.humanit.portal_api.service;

import com.humanit.portal_api.dto.salaries.SalaryComponentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SalaryComponentsService {
    SalaryComponentDTO createSalaryComponent(SalaryComponentDTO componentDTO);
    Page<SalaryComponentDTO> getAllSalariesComponents(Pageable pageable);
    SalaryComponentDTO getSalaryComponentById(UUID id);
    Page<SalaryComponentDTO> getAllSalaryComponentBySalaryId(UUID id, Pageable pageable);
    SalaryComponentDTO updateSalaryComponent(UUID id, SalaryComponentDTO componentDTO);
    void deleteSalaryComponent(UUID id);
}
