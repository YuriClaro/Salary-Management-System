package com.humanit.portal_api.dto.authorization;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object (DTO) of Authentication Response.")
public class AuthResponseDTO {
    @Schema(description = "Token of access of the user", example = "eyJhbGczMTAwODgKkdL-2uCkMZ6XjOZY3BZi_asOPVhumW4tY")
    private String accessToken;

    @Schema(description = "Refresh Token of the user", example = "8f52e189-6f70-4159-a4ee-ace3188d7d0a")
    private String refreshToken;
}
