package com.humanit.portal_api.controller;

import com.humanit.portal_api.dto.salaries.SalaryComponentDTO;
import com.humanit.portal_api.service.SalaryComponentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/portal/salary/components")
@RequiredArgsConstructor
public class SalaryComponentsController {
    private final SalaryComponentsService componentsService;

    @Operation(
            summary = "Create a new Salary Component",
            description = "Creates a new Salary Component with the provided details."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Salary Component created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Salary Component data")
    })
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<SalaryComponentDTO> createSalaryComponent(@Valid @RequestBody SalaryComponentDTO componentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(componentsService.createSalaryComponent(componentDTO));
    }

    @Operation(
            summary = "Get all Salary Components",
            description = "Fetches all Salary Components with pagination support."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of Salary Components fetched successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters")
    })
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Page<SalaryComponentDTO>> getAllSalariesComponents(
            @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(componentsService.getAllSalariesComponents(pageable));
    }

    @Operation(
            summary = "Get Salary Component by ID",
            description = "Fetches a Salary Component by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salary Component fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Salary Component not found")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    ResponseEntity<SalaryComponentDTO> getSalaryComponentById(@PathVariable UUID id) {
        return ResponseEntity.ok(componentsService.getSalaryComponentById(id));
    }
    @Operation(
            summary = "Get all Salary Components by Salary ID",
            description = "Fetches Salary Components filtered by the associated Salary ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salary Components by Salary ID fetched successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Salary ID or pagination parameters")
    })
    @GetMapping("/salaryId={id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Page<SalaryComponentDTO>> getAllSalaryComponentBySalaryId(
            @PathVariable UUID id, @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(componentsService.getAllSalaryComponentBySalaryId(id, pageable));
    }

    @Operation(
            summary = "Update a Salary Component",
            description = "Updates an existing Salary Component based on its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salary Component updated successfully"),
            @ApiResponse(responseCode = "404", description = "Salary Component not found"),
            @ApiResponse(responseCode = "400", description = "Invalid Salary Component data")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<SalaryComponentDTO> updateSalaryComponent(@PathVariable UUID id,
                                                             @Valid @RequestBody SalaryComponentDTO componentDTO) {
        return ResponseEntity.ok(componentsService.updateSalaryComponent(id, componentDTO));
    }

    @Operation(summary = "Delete a Salary Component", description = "Deletes a Salary Component based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Salary Component deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Salary Component not found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> deleteSalaryComponent(@PathVariable UUID id) {
        componentsService.deleteSalaryComponent(id);
        return ResponseEntity.noContent().build();
    }
}
