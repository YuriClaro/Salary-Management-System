package com.humanit.authentication_api.exception.refreshToken;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.humanit.authentication_api.exception.ErrorEnum.REFRESH_TOKEN_NOT_FOUND;

@Getter
public class RefreshTokenNotFoundException extends Exception {
    private final String message;
    private final String code;
    private final HttpStatus status;

    public RefreshTokenNotFoundException(String token) {
        super(REFRESH_TOKEN_NOT_FOUND.getMessage(token));
        this.message = REFRESH_TOKEN_NOT_FOUND.getMessage();
        this.code = REFRESH_TOKEN_NOT_FOUND.getCode();
        this.status = REFRESH_TOKEN_NOT_FOUND.getStatus();
    }
}
