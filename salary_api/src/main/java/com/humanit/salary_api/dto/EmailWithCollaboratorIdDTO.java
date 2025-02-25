package com.humanit.salary_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object representing the recipient's email address.")
public class EmailWithCollaboratorIdDTO {
    @Size(min = 5, max = 150)
    @NotEmpty(message = "Email is required")
    @Schema(description = "The email address of the recipient.", example = "user@example.com", format = "email")
    private String receiver;

    @Schema(description = "UUID of the Collaborator", example = "1a2b3c4d-5e6f-7g8h-9i0j-1234567890ab")
    private UUID id;
}
