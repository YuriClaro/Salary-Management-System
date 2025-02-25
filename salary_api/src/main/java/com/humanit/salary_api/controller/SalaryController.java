package com.humanit.salary_api.controller;

import com.humanit.salary_api.dto.EmailDTO;
import com.humanit.salary_api.dto.SalaryDTO;
import com.humanit.salary_api.dto.SalaryDecisionDTO;
import com.humanit.salary_api.enumerator.SalaryStatus;
import com.humanit.salary_api.exception.collaborator.CollaboratorByIdNotFoundException;
import com.humanit.salary_api.exception.collaborator.InvalidCollaboratorException;
import com.humanit.salary_api.exception.salary.InvalidSalaryDecisionException;
import com.humanit.salary_api.exception.salary.SalaryByIdNotFoundException;
import com.humanit.salary_api.service.SalaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/salary")
@Tag(name = "SalarySpecification API", description = "Operations related to salaries")
public class SalaryController {
    private final SalaryService salaryService;

    @Operation(summary = "Create a new salary",
            description = "Create a new salary and return the created salary data.",
            security = @SecurityRequirement(name = "Bearer Authentication Token"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "SalarySpecification created successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid salary data provided."),
            @ApiResponse(responseCode = "404", description = "Collaborator not found.")
    })
    @PostMapping
    public ResponseEntity<SalaryDTO> createSalary(
            @Valid @RequestBody SalaryDTO salaryDTO) throws CollaboratorByIdNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(salaryService.createSalary(salaryDTO));
    }

    @Operation(summary = "Get all salaries",
            description = "Retrieve a paginated list of all salaries.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of salaries retrieved successfully.")
    })
    @GetMapping
    public ResponseEntity<Page<SalaryDTO>> getAllSalaries(
            @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(salaryService.getAllSalaries(pageable));
    }

    @Operation(summary = "Get salary by ID",
            description = "Retrieve a salary by its unique ID.",
            security = @SecurityRequirement(name = "Bearer Authentication Token"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SalarySpecification retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "SalarySpecification not found.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SalaryDTO> getSalaryById(@PathVariable UUID id) throws SalaryByIdNotFoundException {
        return ResponseEntity.ok(salaryService.getSalaryById(id));
    }

    @Operation(summary = "Get all salaries by collaborator ID",
            description = "Retrieve a paginated list of all salaries associated with a specific collaborator.",
            security = @SecurityRequirement(name = "Bearer Authentication Token"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of salaries retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "Collaborator not found.")
    })
    @GetMapping("/collaboratorId={id}")
    public ResponseEntity<Page<SalaryDTO>> getAllSalariesByCollaboratorId(
            @PathVariable UUID id,
            @ParameterObject Pageable pageable)
            throws CollaboratorByIdNotFoundException {
        return ResponseEntity.ok(salaryService.getAllSalariesByCollaboratorId(id, pageable));
    }

    @Operation(summary = "Update salary",
            description = "Update an existing salary by its ID.",
            security = @SecurityRequirement(name = "Bearer Authentication Token"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SalarySpecification updated successfully."),
            @ApiResponse(responseCode = "404", description = "SalarySpecification not found."),
            @ApiResponse(responseCode = "400", description = "Invalid salary data provided.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SalaryDTO> updateSalary(
            @PathVariable UUID id, @Valid @RequestBody SalaryDTO salaryDTO) throws SalaryByIdNotFoundException {
        return ResponseEntity.ok(salaryService.updateSalary(id, salaryDTO));
    }

    @Operation(summary = "Delete salary",
            description = "Delete a salary by its unique ID.",
            security = @SecurityRequirement(name = "Bearer Authentication Token"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "SalarySpecification deleted successfully."),
            @ApiResponse(responseCode = "404", description = "SalarySpecification not found.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalary(@PathVariable UUID id) throws SalaryByIdNotFoundException {
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
    public ResponseEntity<Page<SalaryDTO>> getAllSalariesByStatusBetween(
            @RequestParam(required = false) List<SalaryStatus> status,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(salaryService.getAllSalariesByStatusBetween(status, startDate, endDate, pageable));
    }

    @Operation(
            summary = "Accept proposed salary",
            description = "Allows a collaborator to accept a proposed salary decision by providing their decision and the associated salary ID.",
            security = @SecurityRequirement(name = "Bearer Authentication Token")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salary proposal accepted successfully."),
            @ApiResponse(responseCode = "404", description = "Salary or collaborator not found."),
            @ApiResponse(responseCode = "400", description = "Invalid salary decision or collaborator.")
    })
    @PostMapping("/proposed/salaryId={id}")
    public ResponseEntity<SalaryDTO> acceptSalaryProposed(
            @PathVariable UUID id, @Valid @RequestBody SalaryDecisionDTO salaryDecisionDTO)
            throws SalaryByIdNotFoundException, InvalidSalaryDecisionException, InvalidCollaboratorException,
            CollaboratorByIdNotFoundException {
        return ResponseEntity.ok(salaryService.acceptSalaryProposed(id, salaryDecisionDTO));
    }

    @Operation(
            summary = "Get own salaries by collaborator ID",
            description = "Retrieve a paginated list of salaries associated with the authenticated collaborator. Only the collaborator can view their own salaries.",
            security = @SecurityRequirement(name = "Bearer Authentication Token")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of salaries retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "Collaborator not found."),
            @ApiResponse(responseCode = "400", description = "Invalid collaborator ID.")
    })
    @PostMapping("/my-salaries")
    ResponseEntity<Page<SalaryDTO>> getOwnSalariesByCollaboratorId(
            @ParameterObject Pageable pageable, @RequestBody EmailDTO emailDTO)
            throws InvalidCollaboratorException, CollaboratorByIdNotFoundException {
        return ResponseEntity.ok(salaryService.getOwnSalariesByCollaboratorId(pageable, emailDTO));
    }
}
