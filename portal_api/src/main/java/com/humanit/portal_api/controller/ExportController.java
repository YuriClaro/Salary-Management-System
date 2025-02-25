package com.humanit.portal_api.controller;

import com.humanit.portal_api.dto.export.DatesDTO;
import com.humanit.portal_api.dto.export.EmailResponseDTO;
import com.humanit.portal_api.dto.export.SalaryStatusDTO;
import com.humanit.portal_api.service.impl.ExportServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/portal/export")
public class ExportController {
    private final ExportServiceImpl exportServiceImpl;

    @Operation(
            summary = "Export all salaries",
            description = "Exports all salaries to an external file, triggered by an authorized request."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Salaries exported successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access")
    })
    @PostMapping("/all/salaries")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<EmailResponseDTO> exportAllSalaries(HttpServletRequest request) {
        return ResponseEntity.ok().body(exportServiceImpl.exportAllSalaries(request));
    }

    @Operation(
            summary = "Export all salaries between dates",
            description = "Exports all salaries between dates to an external file, triggered by an authorized request."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Salaries exported successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access")
    })
    @PostMapping("/dates")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<EmailResponseDTO> exportAllSalariesBetweenDates(
            HttpServletRequest request, @RequestBody DatesDTO datesDTO) {
        return ResponseEntity.ok().body(
                exportServiceImpl.exportAllSalariesBetweenDates(request, datesDTO));
    }

    @Operation(
            summary = "Export all salaries by status",
            description = "Exports all salaries by status to an external file, triggered by an authorized request."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Salaries exported successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access")
    })
    @PostMapping("/status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<EmailResponseDTO> exportAllSalariesByStatus(
            HttpServletRequest request, @Valid @RequestBody SalaryStatusDTO salaryStatusDTO) {
        return ResponseEntity.ok().body(exportServiceImpl.exportAllSalariesByStatus(request, salaryStatusDTO));
    }

    @Operation(
            summary = "Export salary by collaborator ID",
            description = "Exports salary by collaborator ID to an external file, triggered by an authorized request."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Salary exported successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/collaboratorId={id}")
    public ResponseEntity<EmailResponseDTO> exportSalaryByCollaboratorId(
            HttpServletRequest request, @PathVariable UUID id) {
        return ResponseEntity.ok().body(exportServiceImpl.exportSalaryByCollaboratorId(request, id));
    }

    @Operation(
            summary = "Export own salary by user authenticated",
            description = "Exports own salary by user authenticated to an external file, triggered by an authorized request."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Salary exported successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access")
    })
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/my-salaries")
    public ResponseEntity<EmailResponseDTO> exportAllOwnSalaries(HttpServletRequest request) {
        return ResponseEntity.ok().body(exportServiceImpl.exportAllOwnSalaries(request));
    }
}
