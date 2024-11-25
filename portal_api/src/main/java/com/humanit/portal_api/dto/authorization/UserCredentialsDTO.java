package com.humanit.portal_api.dto.authorization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.humanit.portal_api.enumerator.UserCredentialsRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Data Transfer Object (DTO) of User Credentials.")
public class UserCredentialsDTO {
    @Schema(description = "Unique identifier for the user", example = "e12a567d-32f8-4e9a-9073-6781f9d5e423")
    private UUID id;

    @Size(min = 5, max = 30)
    @NotEmpty(message = "username is required")
    @Schema(description = "User's username", example = "alice_silva")
    private String username;

    @Size(min = 5, max = 150)
    @Email(message = "Invalid email format")
    @NotEmpty(message = "email is required")
    @Schema(description = "User's email", example = "alice.silva@example.com")
    private String email;

    @NotEmpty(message = "role is required")
    @Schema(description = "User role", example = "ADMIN")
    private UserCredentialsRole role;

    @Size(min = 8, max = 128)
    @NotEmpty(message = "password is required")
    @Schema(description = "User's password", example = "P@$$W0RD3142!")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
