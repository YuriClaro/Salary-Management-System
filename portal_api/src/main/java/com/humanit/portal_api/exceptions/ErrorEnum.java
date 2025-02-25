package com.humanit.portal_api.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ErrorEnum {
    GENERIC_EXCEPTION("GENERIC_EXCEPTION", "An unexpected error occurred. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR),
    REFRESH_TOKEN_EXPIRED("REFRESH_TOKEN_EXPIRED", "Your session has expired: %s, please sign-in again.", HttpStatus.UNAUTHORIZED),
    SALARY_COMPONENT_NOT_FOUND("SALARY_COMPONENT_NOT_FOUND", "Salary with ID: %s component not found.", HttpStatus.NOT_FOUND),
    COLLABORATOR_NOT_FOUND("COLLABORATOR_NOT_FOUND", "Collaborator not found with ID: %s", HttpStatus.NOT_FOUND),
    USER_NOT_AUTHENTICATED("USER_NOT_AUTHENTICATED", "User %s is not authenticated.", HttpStatus.UNAUTHORIZED),
    INVALID_HEADER("INVALID_HEADER", "Authorization header is missing or invalid.", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN("INVALID_TOKEN", "Token is invalid or username does not match", HttpStatus.UNAUTHORIZED),
    REFRESH_TOKEN_NOT_FOUND("REFRESH_TOKEN_NOT_FOUND", "Refresh token not found.", HttpStatus.NOT_FOUND),
    SALARY_INVALID_DECISION("SALARY_INVALID_DECISION", "An unexpected error occured. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR),
    SALARY_NOT_FOUND("SALARY_NOT_FOUND", "Salary with ID: %s not found.", HttpStatus.NOT_FOUND);

    @Getter
    private final String code;
    private final String message;
    @Getter
    private final HttpStatus status;

    ErrorEnum(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
