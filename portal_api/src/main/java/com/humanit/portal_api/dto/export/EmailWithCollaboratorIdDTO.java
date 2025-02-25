package com.humanit.portal_api.dto.export;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "The email address of the recipient.", example = "user@example.com", format = "email")
    private String receiver;

    @Schema(description = "UUID of the Collaborator", example = "1a2b3c4d-5e6f-7g8h-9i0j-1234567890ab")
    private UUID id;
}
