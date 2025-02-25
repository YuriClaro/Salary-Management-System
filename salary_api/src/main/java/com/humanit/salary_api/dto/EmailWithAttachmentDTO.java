package com.humanit.salary_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
@Schema(description = "Data Transfer Object representing the recipient's email and an optional file attachment.")
public class EmailWithAttachmentDTO {
    @Size(min = 5, max = 150)
    @NotEmpty(message = "Email is required")
    @Schema(description = "The email address of the recipient.", example = "user@example.com", format = "email")
    private String receiver;

    @Schema(description = "The attached report in binary format, typically an Excel file.")
    private byte[] excelFile;
}
