package com.humanit.portal_api.client;

import com.humanit.portal_api.dto.export.EmailDTO;
import com.humanit.portal_api.dto.salaries.SalaryDTO;
import com.humanit.portal_api.dto.salaries.SalaryDecisionDTO;
import com.humanit.portal_api.enumerator.SalaryStatus;
import com.humanit.portal_api.exceptions.salaryApi.InvalidSalaryDecisionException;
import com.humanit.portal_api.exceptions.salaryApi.SalaryByIdNotFoundException;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@FeignClient(name = "salaryFeignClient", url = "${microservice.salary_api}")
public interface SalaryFeignClient {
    @PostMapping
    ResponseEntity<SalaryDTO> createSalary(@Valid @RequestBody SalaryDTO salaryDTO);

    @GetMapping
    ResponseEntity<Page<SalaryDTO>> getAllSalaries(@ParameterObject Pageable pageable);

    @GetMapping("/{id}")
    ResponseEntity<SalaryDTO> getSalaryById(@PathVariable UUID id);

    @GetMapping("/collaboratorId={id}")
    ResponseEntity<Page<SalaryDTO>> getAllSalariesByCollaboratorId(
            @PathVariable UUID id, @ParameterObject Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<SalaryDTO> updateSalary(@PathVariable UUID id, @Valid @RequestBody SalaryDTO salaryDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteSalary(@PathVariable UUID id);

    @GetMapping("/search")
    ResponseEntity<Page<SalaryDTO>> getAllSalariesByStatusBetween(
            @RequestParam(required = false) List<SalaryStatus> status,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @ParameterObject Pageable pageable);

    @PostMapping("/proposed/salaryId={id}")
    ResponseEntity<SalaryDTO> acceptSalaryProposed(
            @PathVariable UUID id, @Valid @RequestBody SalaryDecisionDTO salaryDecisionDTO)
            throws SalaryByIdNotFoundException, InvalidSalaryDecisionException;

    @PostMapping("/my-salaries")
    ResponseEntity<Page<SalaryDTO>> getAllOwnSalaries(
            @ParameterObject Pageable pageable, @RequestBody EmailDTO emailDTO);
}
