package com.humanit.portal_api.dto.export;

import com.humanit.portal_api.enumerator.SalaryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object representing the recipient's email address.")
public class SalaryStatusDTO {
    @NotNull(message = "Salary status is required")
    @Schema(description = "Status of the salary", example = "ACTIVE")
    private SalaryStatus status;
}
