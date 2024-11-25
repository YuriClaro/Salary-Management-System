package com.humanit.portal_api.dto.authorization;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object (DTO) of Authentication Request.")
public class AuthRequestDTO {
    @Size(min = 5, max = 30)
    @NotEmpty(message = "username is required")
    @Schema(description = "User's username", example = "alice_silva")
    private String username;

    @Size(min = 8, max = 128)
    @NotEmpty(message = "password is required")
    @Schema(description = "User's password", example = "P@$$W0RD")
    private String password;
}
