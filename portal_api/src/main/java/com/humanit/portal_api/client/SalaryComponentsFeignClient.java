package com.humanit.portal_api.client;

import com.humanit.portal_api.dto.salaries.SalaryComponentDTO;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "salaryComponentsFeignClient", url = "${microservice.salary_components_api}")
public interface SalaryComponentsFeignClient {
    @PostMapping
    ResponseEntity<SalaryComponentDTO> createSalaryComponent(@Valid @RequestBody SalaryComponentDTO componentDTO);

    @GetMapping
    ResponseEntity<Page<SalaryComponentDTO>> getAllSalariesComponents(
            @ParameterObject Pageable pageable);

    @GetMapping("/{id}")
    ResponseEntity<SalaryComponentDTO> getSalaryComponentById(@PathVariable UUID id);

    @GetMapping("/salaryId={id}")
    ResponseEntity<Page<SalaryComponentDTO>> getAllSalaryComponentBySalaryId(
            @PathVariable UUID id, @ParameterObject Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<SalaryComponentDTO> updateSalaryComponent(
            @PathVariable UUID id, @Valid @RequestBody SalaryComponentDTO componentDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteSalaryComponent(@PathVariable UUID id);
}
