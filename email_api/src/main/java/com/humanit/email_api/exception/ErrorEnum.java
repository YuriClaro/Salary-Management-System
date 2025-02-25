package com.humanit.email_api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ErrorEnum {
    EMAIL_SEND_EXCEPTION("EMAIL_SEND_EXCEPTION", "Error while sending email.", HttpStatus.INTERNAL_SERVER_ERROR),
    EMAIL_PROCESSING_EXCEPTION("EMAIL_PROCESSING_EXCEPTION", "Unexpected error while processing email.", HttpStatus.INTERNAL_SERVER_ERROR),
    GENERIC_EXCEPTION("GENERIC_EXCEPTION", "An unexpected error occurred. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);

    @Getter
    private final String code;
    @Getter
    private final HttpStatus status;
    private final String message;

    ErrorEnum(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
