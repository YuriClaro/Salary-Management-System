package com.humanit.salary_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object (DTO) representing a date range to query current salaries.")
public class DatesRequestDTO {
    @Size(min = 5, max = 150)
    @NotEmpty(message = "Email is required")
    @Schema(description = "The email address of the recipient.", example = "user@example.com", format = "email")
    private String receiver;

    @NotBlank
    @Schema(description = "The start date of the range for querying current salaries.", example = "2024-11-01")
    private LocalDate initDate;

    @NotBlank
    @Schema(description = "The end date of the range for querying current salaries.", example = "2024-11-20")
    private LocalDate endDate;
}
