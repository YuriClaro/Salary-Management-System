package com.humanit.salary_api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ErrorEnum {
    SALARY_NOT_FOUND("SALARY_NOT_FOUND", "SalarySpecification with ID: %s not found.", HttpStatus.NOT_FOUND),
    SALARY_COMPONENT_NOT_FOUND("SALARY_COMPONENT_NOT_FOUND", "SalarySpecification with ID: %s component not found.", HttpStatus.NOT_FOUND),
    COLLABORATOR_NOT_FOUND("COLLABORATOR_NOT_FOUND", "Collaborator not found with ID: %s", HttpStatus.NOT_FOUND),
    OPTIMISTIC_LOCKING_EXCEPTION("OPTIMISTIC_LOCKING_EXCEPTION", "Converter update unlocked. Please try again", HttpStatus.CONFLICT),
    GENERIC_EXCEPTION("GENERIC_EXCEPTION", "An unexpected error occurred. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR),
    SALARY_INVALID_DECISION("SALARY_INVALID_DECISION", "An unexpected error occured. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_COLLABORATOR("INVALID_COLLABORATOR", "Invalid data between collaborator and User authenticated.", HttpStatus.FORBIDDEN),
    SALARY_EXPORT_EXCEPTION("SALARY_EXPORT_EXCEPTION", "An unexpected error occurred when exporting the salary.", HttpStatus.INTERNAL_SERVER_ERROR);

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
