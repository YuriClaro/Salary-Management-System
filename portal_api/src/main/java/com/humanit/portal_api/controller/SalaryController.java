package com.humanit.portal_api.controller;

import com.humanit.portal_api.dto.salaries.SalaryDTO;
import com.humanit.portal_api.dto.salaries.SalaryDecisionDTO;
import com.humanit.portal_api.enumerator.SalaryStatus;
import com.humanit.portal_api.exceptions.salaryApi.InvalidSalaryDecisionException;
import com.humanit.portal_api.exceptions.salaryApi.SalaryByIdNotFoundException;
import com.humanit.portal_api.service.SalaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/portal/salary")
@RequiredArgsConstructor
public class SalaryController {
    private final SalaryService salaryService;

    @Operation(
            summary = "Create a new Salary",
            description = "Creates a new Salary with the provided details."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Salary created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Salary data")
    })
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<SalaryDTO> createSalary(@Valid @RequestBody SalaryDTO salaryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(salaryService.createSalary(salaryDTO));
    }

    @Operation(
            summary = "Get all Salaries",
            description = "Retrieves a paginated list of all Salaries."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salaries retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access")
    })
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Page<SalaryDTO>> getAllSalaries(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(salaryService.getAllSalaries(pageable));
    }

    @Operation(
            summary = "Get Salary by ID",
            description = "Retrieves a Salary by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salary retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Salary not found")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    ResponseEntity<SalaryDTO> getSalaryById(@PathVariable UUID id) {
        return ResponseEntity.ok(salaryService.getSalaryById(id));
    }

    @Operation(
            summary = "Get all Salaries by Collaborator ID",
            description = "Retrieves a paginated list of all Salaries associated with a specific collaborator."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salaries retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access")
    })
    @GetMapping("/collaboratorId={id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Page<SalaryDTO>> getAllSalariesByCollaboratorId(
            @PathVariable UUID id, @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(salaryService.getAllSalariesByCollaboratorId(id, pageable));
    }

    @Operation(
            summary = "Update a Salary",
            description = "Updates the details of an existing Salary based on its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salary updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Salary data"),
            @ApiResponse(responseCode = "404", description = "Salary not found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<SalaryDTO> updateSalary(@PathVariable UUID id, @Valid @RequestBody SalaryDTO salaryDTO) {
        return ResponseEntity.ok(salaryService.updateSalary(id, salaryDTO));
    }

    @Operation(
            summary = "Delete a Salary",
            description = "Deletes a Salary based on its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Salary deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Salary not found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> deleteSalary(@PathVariable UUID id) {
        salaryService.deleteSalary(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Search salaries by status and date range",
            description = "Fetches a paginated list of salaries based on the provided status and effective date range.",
            security = @SecurityRequirement(name = "Bearer Authentication Token"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salaries fetched successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid parameters provided."),
            @ApiResponse(responseCode = "404", description = "Salaries not found.")
    })
    @GetMapping("/search")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Page<SalaryDTO>> getAllSalariesByStatusBetween(
            @RequestParam(required = false) List<SalaryStatus> status,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(salaryService.getAllSalariesByStatusBetween(status, startDate, endDate, pageable));
    }

    @Operation(
            summary = "Accept proposed salary",
            description = "Allows a collaborator to accept a proposed salary decision by providing their decision " +
                    "and the associated salary ID.",
            security = @SecurityRequirement(name = "Bearer Authentication Token")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salary proposal accepted successfully."),
            @ApiResponse(responseCode = "404", description = "Salary or collaborator not found."),
            @ApiResponse(responseCode = "400", description = "Invalid salary decision or collaborator.")
    })
    @PostMapping("/proposed/salaryId={id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<SalaryDTO> acceptSalaryProposed(
            @PathVariable UUID id, @Valid @RequestBody SalaryDecisionDTO salaryDecisionDTO, HttpServletRequest request)
            throws SalaryByIdNotFoundException, InvalidSalaryDecisionException {
        return ResponseEntity.ok(salaryService.acceptSalaryProposed(id, salaryDecisionDTO, request));
    }

    @Operation(
            summary = "Get own salaries by collaborator ID",
            description = "Retrieve a paginated list of salaries associated with the authenticated collaborator. " +
                    "Only the collaborator can view their own salaries.",
            security = @SecurityRequirement(name = "Bearer Authentication Token")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of salaries retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "Collaborator not found."),
            @ApiResponse(responseCode = "400", description = "Invalid collaborator ID.")
    })
    @PostMapping("/my-salaries")
    @PreAuthorize("hasAuthority('USER')")
    ResponseEntity<Page<SalaryDTO>> getOwnSalaries(
            @ParameterObject Pageable pageable, HttpServletRequest request) {
        return ResponseEntity.ok(salaryService.getAllOwnSalaries(pageable, request));
    }
}
