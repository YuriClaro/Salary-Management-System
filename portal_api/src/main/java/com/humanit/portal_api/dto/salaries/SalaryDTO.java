package com.humanit.portal_api.dto.salaries;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.humanit.portal_api.enumerator.SalaryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Data Transfer Object (DTO) for salary details.")
public class SalaryDTO {
    @Schema(description = "Unique identifier for the salary", example = "1a2b3c4d-5e6f-7g8h-9i0j-1234567890ab")
    private UUID id;

    @Schema(description = "collaborator associated with the salary")
    private UUID collaboratorId;

    @Schema(description = "Status of the salary", example = "ACTIVE")
    @NotNull(message = "Salary status is required")
    private SalaryStatus status;

    @Schema(description = "List of components that make up the salary")
    private List<SalaryComponentDTO> salaryComponentList;

    @Schema(description = "Date when the salary was presented", example = "2023-05-01")
    @Future(message = "Effective date must be in the future")
    private LocalDate presentationDate;

    @Schema(description = "Date when the salary was accepted", example = "2023-06-01")
    @Future(message = "Effective date must be in the future")
    private LocalDate acceptanceDate;

    @Schema(description = "Date when the salary became effective", example = "2023-07-01")
    @Future(message = "Effective date must be in the future")
    private LocalDate effectiveDate;

    @Schema(description = "Version of the salary provided by Optimistic Locking")
    private Long version;
}
