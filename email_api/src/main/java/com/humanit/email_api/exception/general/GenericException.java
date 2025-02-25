package com.humanit.email_api.exception.general;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.humanit.email_api.exception.ErrorEnum.GENERIC_EXCEPTION;

@Getter
public class GenericException extends Exception {
    private final String message;
    private final String code;
    private final HttpStatus status;

    public GenericException() {
        super(GENERIC_EXCEPTION.getMessage());
        this.message = GENERIC_EXCEPTION.getMessage();
        this.code = GENERIC_EXCEPTION.getCode();
        this.status = GENERIC_EXCEPTION.getStatus();
    }
}
