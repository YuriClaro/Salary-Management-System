package com.humanit.email_api.exception.email;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.humanit.email_api.exception.ErrorEnum.EMAIL_PROCESSING_EXCEPTION;

@Getter
public class EmailProcessingException extends Exception {
    private final String message;
    private final String code;
    private final HttpStatus status;

    public EmailProcessingException() {
        super(EMAIL_PROCESSING_EXCEPTION.getMessage());
        this.message = EMAIL_PROCESSING_EXCEPTION.getMessage();
        this.code = EMAIL_PROCESSING_EXCEPTION.getCode();
        this.status = EMAIL_PROCESSING_EXCEPTION.getStatus();
    }
}


