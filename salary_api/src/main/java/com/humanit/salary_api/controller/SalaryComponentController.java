package com.humanit.salary_api.controller;

import com.humanit.salary_api.dto.SalaryComponentDTO;
import com.humanit.salary_api.exception.salary.SalaryByIdNotFoundException;
import com.humanit.salary_api.exception.salaryComponent.SalaryComponentByIdNotFoundException;
import com.humanit.salary_api.service.SalaryComponentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/salary/components")
@Tag(name = "SalarySpecification API", description = "Endpoints to manage salary components.")
public class SalaryComponentController {
    private final SalaryComponentService componentService;

    @Operation(
            summary = "Create a new salary component.",
            description = "Creates a new salary component and returns the created component details.",
            security = @SecurityRequirement(name = "Bearer Authentication Token"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "SalarySpecification Component created successfully.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SalaryComponentDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid SalarySpecification Component data provided.",
                            content = @Content)
            }
    )
    @PostMapping
    public ResponseEntity<SalaryComponentDTO> createSalaryComponent(
            @Valid @RequestBody SalaryComponentDTO componentDTO) throws SalaryByIdNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(componentService.createSalaryComponent(componentDTO));
    }

    @Operation(
            summary = "Get all salary components.",
            description = "Retrieve a paginated list of all salary components.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "SalarySpecification Components retrieved successfully.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Page.class)))
            }
    )
    @GetMapping
    public ResponseEntity<Page<SalaryComponentDTO>> getAllSalaryComponents(
            @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(componentService.getAllSalaryComponents(pageable));
    }

    @Operation(
            summary = "Get a salary component by ID.",
            description = "Retrieve details of a specific salary component by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "SalarySpecification Component retrieved successfully.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SalaryComponentDTO.class))),
                    @ApiResponse(responseCode = "404", description = "SalarySpecification Component not found.",
                            content = @Content)
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<SalaryComponentDTO> getSalaryComponentById(
            @PathVariable @Schema(description = "UUID of the salary component.",
                    example = "123e4567-e89b-12d3-a456-426614174000") UUID id)
            throws SalaryComponentByIdNotFoundException {
        return ResponseEntity.ok(componentService.getSalaryComponentById(id));
    }

    @Operation(
            summary = "Get salary components by SalarySpecification ID.",
            description = "Retrieve a paginated list of salary components associated with a specific salary ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "SalarySpecification Components retrieved successfully.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Page.class))),
                    @ApiResponse(responseCode = "404", description = "SalarySpecification not found.",
                            content = @Content)
            }
    )
    @GetMapping("/salaryId={id}")
    public ResponseEntity<Page<SalaryComponentDTO>> getSalaryComponentsBySalaryId(
            @PathVariable @Schema(description = "UUID of the salary.",
                    example = "123e4567-e89b-12d3-a456-426614174000") UUID id,
            @ParameterObject Pageable pageable)
            throws SalaryByIdNotFoundException {
        return ResponseEntity.ok(componentService.getAllSalaryComponentBySalaryId(id, pageable));
    }

    @Operation(
            summary = "Update a salary component.",
            description = "Update the details of an existing salary component by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "SalarySpecification Component updated successfully.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SalaryComponentDTO.class))),
                    @ApiResponse(responseCode = "404", description = "SalarySpecification Component not found.",
                            content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid SalarySpecification Component data provided.",
                            content = @Content)
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<SalaryComponentDTO> updateSalaryComponent(
            @PathVariable @Schema(description = "UUID of the salary component.",
                    example = "123e4567-e89b-12d3-a456-426614174000") UUID id,
            @Valid @RequestBody SalaryComponentDTO componentDTO)
            throws SalaryComponentByIdNotFoundException {
        return ResponseEntity.ok(componentService.updateSalaryComponent(id, componentDTO));
    }

    @Operation(
            summary = "Delete a salary component.",
            description = "Delete an existing salary component by its ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "SalarySpecification Component deleted successfully."),
                    @ApiResponse(responseCode = "404", description = "SalarySpecification Component not found.",
                            content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalaryComponent(
            @PathVariable @Schema(description = "UUID of the salary component.",
                    example = "123e4567-e89b-12d3-a456-426614174000") UUID id)
            throws SalaryComponentByIdNotFoundException {
        componentService.deleteSalaryComponent(id);
        return ResponseEntity.noContent().build();
    }
}
