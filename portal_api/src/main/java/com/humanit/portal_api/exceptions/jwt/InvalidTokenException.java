package com.humanit.portal_api.exceptions.jwt;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.humanit.portal_api.exceptions.ErrorEnum.INVALID_TOKEN;

@Getter
public class InvalidTokenException extends Exception{
    private final String message;
    private final String code;
    private final HttpStatus status;

    public InvalidTokenException() {
        super(INVALID_TOKEN.getMessage());
        this.message = INVALID_TOKEN.getMessage();
        this.code = INVALID_TOKEN.getCode();
        this.status = INVALID_TOKEN.getStatus();
    }
}
