package com.humanit.portal_api.dto.salaries;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.humanit.portal_api.enumerator.ComponentType;
import com.humanit.portal_api.enumerator.CoverFlex;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Data Transfer Object (DTO) for salary component details.")
public class SalaryComponentDTO {
    @Schema(description = "Unique identifier for the salary component", example = "a1b2c3d4-5678-90ef-ghij-1234567890kl")
    private UUID id;

    @Schema(description = "salary associated with this salary component")
    private UUID salaryId;

    @Schema(description = "Type of the salary component", example = "BONUS")
    @NotNull(message = "Component type is required")
    private ComponentType type;

    @Schema(description = "CoverFlex option for the component", example = "YES")
    private CoverFlex coverFlex;

    @Schema(description = "Value of the salary component", example = "1000.00")
    @NotNull(message = "Value is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Value must be greater than 0")
    private BigDecimal value;

    @Schema(description = "Version of the salary provided by Optimistic Locking")
    private Long version;
}
