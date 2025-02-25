package com.humanit.portal_api.dto.authorization;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object (DTO) of Refresh Token Response.")
public class RefreshTokenRequestDTO {
    @Schema(description = "Refresh Token of the user", example = "8f52e189-6f70-4159-a4ee-ace3188d7d0a")
    private String token;
}
