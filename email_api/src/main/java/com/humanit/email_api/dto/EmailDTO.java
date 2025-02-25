package com.humanit.email_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "DTO representing the details of an email")
public class EmailDTO {
    @NotBlank
    @Email(message = "Invalid email format")
    @Size(min = 5, max = 150)
    @Schema(description = "The email address of the sender", example = "sender@example.com", required = true)
    private String sender;

    @NotBlank
    @Email(message = "Invalid email format")
    @Size(min = 5, max = 150)
    @Schema(description = "The email address of the receiver", example = "receiver@example.com", required = true)
    private String receiver;

    @NotBlank
    @Size(min = 1, max = 255)
    @Schema(description = "The subject of the email", example = "Monthly Salary Report", required = true)
    private String subject;

    @NotBlank
    @Schema(description = "The text content of the email", example = "Please find attached the salary report.", required = true)
    private String text;

    @Schema(description = "An optional attachment for the email, in byte array format")
    private byte[] attachment;
}
