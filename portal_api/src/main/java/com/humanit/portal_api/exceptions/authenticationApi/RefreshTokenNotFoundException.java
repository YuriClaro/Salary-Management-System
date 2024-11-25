package com.humanit.portal_api.exceptions.authenticationApi;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.humanit.portal_api.exceptions.ErrorEnum.REFRESH_TOKEN_NOT_FOUND;

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
