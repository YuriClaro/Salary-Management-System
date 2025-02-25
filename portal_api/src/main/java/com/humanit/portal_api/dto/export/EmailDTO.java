package com.humanit.portal_api.dto.export;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object representing the recipient's email address.")
public class EmailDTO {
    @Schema(description = "The email address of the recipient.", example = "user@example.com", format = "email")
    private String receiver;
}
