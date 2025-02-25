package com.humanit.portal_api.exceptions.salaryApi;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static com.humanit.portal_api.exceptions.ErrorEnum.COLLABORATOR_NOT_FOUND;

@Getter
public class CollaboratorByIdNotFoundException extends Exception {
    private final String message;
    private final String code;
    private final HttpStatus status;

    public CollaboratorByIdNotFoundException(UUID id) {
        super(COLLABORATOR_NOT_FOUND.getMessage(id));
        this.message = COLLABORATOR_NOT_FOUND.getMessage(id);
        this.code = COLLABORATOR_NOT_FOUND.getCode();
        this.status = COLLABORATOR_NOT_FOUND.getStatus();
    }
}
