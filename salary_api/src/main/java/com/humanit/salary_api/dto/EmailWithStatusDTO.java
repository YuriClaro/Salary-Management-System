package com.humanit.salary_api.dto;

import com.humanit.salary_api.enumerator.SalaryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object representing the recipient's email address.")
public class EmailWithStatusDTO {
    @Size(min = 5, max = 150)
    @NotEmpty(message = "Email is required")
    @Schema(description = "The email address of the recipient.", example = "user@example.com", format = "email")
    private String receiver;

    @Schema(description = "Status of the salary", example = "ACTIVE")
    @NotNull(message = "SalarySpecification status is required")
    private SalaryStatus status;
}
