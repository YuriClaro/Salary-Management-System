package com.humanit.portal_api.dto.export;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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
public class DatesDTO {
    @NotNull
    @Schema(description = "The start date of the range for querying current salaries.", example = "2024-11-01")
    private LocalDate initDate;

    @NotNull
    @Schema(description = "The end date of the range for querying current salaries.", example = "2024-11-20")
    private LocalDate endDate;
}
