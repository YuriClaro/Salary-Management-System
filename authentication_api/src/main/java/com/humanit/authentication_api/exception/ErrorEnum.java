package com.humanit.authentication_api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ErrorEnum {
    USER_NOT_AUTHENTICATED("USER_NOT_AUTHENTICATED", "User %s is not authenticated.", HttpStatus.UNAUTHORIZED),
    REFRESH_TOKEN_EXPIRED("REFRESH_TOKEN_EXPIRED", "Your session has expired: %s, please sign-in again.", HttpStatus.UNAUTHORIZED),
    REFRESH_TOKEN_NOT_FOUND("REFRESH_TOKEN_NOT_FOUND", "Refresh token not found.", HttpStatus.NOT_FOUND),
    GENERIC_EXCEPTION("GENERIC_EXCEPTION", "An unexpected error occurred. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR),
    OPTIMISTIC_LOCKING_EXCEPTION("OPTIMISTIC_LOCKING_EXCEPTION", "Converter update unlocked. Please try again", HttpStatus.CONFLICT);

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
