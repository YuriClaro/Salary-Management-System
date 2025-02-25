package com.humanit.salary_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.humanit.salary_api.enumerator.SalaryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Data Transfer Object (DTO) for salary decision details.")
public class SalaryDecisionDTO {
    @Schema(description = "Type of the salary decision", example = "REJECT")
    @NotNull(message = "salary decision is required")
    private SalaryStatus decision;

    @Schema(description = "The email address of the user credentials.", example = "user@example.com", format = "email")
    private String email;
}
