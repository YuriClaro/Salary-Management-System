package com.humanit.portal_api.dto.export;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object (DTO) of Response Email Export.")
public class EmailResponseDTO {
    @Schema(description = "Message indicating the result of the salary export operation.",
            example = "Salary export request processed successfully.")
    private String message;

    @Schema(description = "The email address where the salary export was sent.", example = "user@example.com")
    private String email;

    @Schema(description = "Timestamp indicating when the response was generated.", example = "2024-11-20T14:30:00")
    private LocalDateTime timestamp;

    public EmailResponseDTO(String message, String email) {
        this.message = message;
        this.email = email;
        this.timestamp = LocalDateTime.now();
    }

}
