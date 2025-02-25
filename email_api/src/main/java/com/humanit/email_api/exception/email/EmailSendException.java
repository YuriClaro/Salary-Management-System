package com.humanit.email_api.exception.email;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.humanit.email_api.exception.ErrorEnum.EMAIL_SEND_EXCEPTION;

@Getter
public class EmailSendException extends Exception {
    private final String message;
    private final String code;
    private final HttpStatus status;

    public EmailSendException() {
        super(EMAIL_SEND_EXCEPTION.getMessage());
        this.message = EMAIL_SEND_EXCEPTION.getMessage();
        this.code = EMAIL_SEND_EXCEPTION.getCode();
        this.status = EMAIL_SEND_EXCEPTION.getStatus();
    }
}


