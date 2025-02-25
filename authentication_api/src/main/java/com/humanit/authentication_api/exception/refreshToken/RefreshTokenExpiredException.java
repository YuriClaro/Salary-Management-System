package com.humanit.authentication_api.exception.refreshToken;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.humanit.authentication_api.exception.ErrorEnum.REFRESH_TOKEN_EXPIRED;

@Getter
public class RefreshTokenExpiredException extends Exception {
    private final String message;
    private final String code;
    private final HttpStatus status;

    public RefreshTokenExpiredException() {
        super(REFRESH_TOKEN_EXPIRED.getMessage());
        this.message = REFRESH_TOKEN_EXPIRED.getMessage();
        this.code = REFRESH_TOKEN_EXPIRED.getCode();
        this.status = REFRESH_TOKEN_EXPIRED.getStatus();
    }
}

