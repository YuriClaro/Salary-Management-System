package com.humanit.salary_api.exception.collaborator;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.humanit.salary_api.exception.ErrorEnum.INVALID_COLLABORATOR;

@Getter
public class InvalidCollaboratorException extends Exception {
    private final String message;
    private final String code;
    private final HttpStatus status;

    public InvalidCollaboratorException() {
        super(INVALID_COLLABORATOR.getMessage());
        this.message = INVALID_COLLABORATOR.getMessage();
        this.code = INVALID_COLLABORATOR.getCode();
        this.status = INVALID_COLLABORATOR.getStatus();
    }
}
