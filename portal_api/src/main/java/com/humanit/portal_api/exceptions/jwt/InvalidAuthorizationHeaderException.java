package com.humanit.portal_api.exceptions.jwt;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.humanit.portal_api.exceptions.ErrorEnum.INVALID_HEADER;

@Getter
public class InvalidAuthorizationHeaderException extends Exception {
    private final String message;
    private final String code;
    private final HttpStatus status;

    public InvalidAuthorizationHeaderException() {
        this.message = INVALID_HEADER.getMessage();
        this.code = INVALID_HEADER.getCode();
        this.status = INVALID_HEADER.getStatus();
    }
}
