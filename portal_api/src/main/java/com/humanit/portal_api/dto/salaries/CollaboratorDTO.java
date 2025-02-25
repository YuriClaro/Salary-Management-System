package com.humanit.portal_api.dto.salaries;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.humanit.portal_api.enumerator.Position;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Data Transfer Object (DTO) used for transferring Collaboration information between processes.")
public class CollaboratorDTO {
    @Schema(description = "Unique identifier for the collaborator", example = "x3sot41e3-5t3b-u7h3-8c11-e516kd8b3c21")
    private UUID id;

    @Size(min = 2, max = 255)
    @NotEmpty(message = "Name is required")
    @Schema(description = "Name of the collaborator", example = "João")
    private String name;

    @Size(min = 5, max = 150)
    @NotEmpty(message = "Email is required")
    @Schema(description = "Email of the collaborator", example = "joao@example.com")
    private String email;

    @NotNull(message = "Position is required")
    @Schema(description = "Position of the collaborator", example = "OPERATIONAL")
    private Position position;

    @Schema(description = "Salary of the collaborator")
    private List<SalaryDTO> salaries;

    @Schema(description = "Version of the salary provided by Optimistic Locking")
    private Long version;
}
