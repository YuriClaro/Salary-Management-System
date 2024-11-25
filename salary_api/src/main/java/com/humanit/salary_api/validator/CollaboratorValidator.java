package com.humanit.salary_api.validator;

import com.humanit.salary_api.exception.collaborator.CollaboratorByIdNotFoundException;
import com.humanit.salary_api.model.Collaborator;
import com.humanit.salary_api.repository.CollaboratorRepository;

import java.util.UUID;

public class CollaboratorValidator {
    public static Collaborator findCollaboratorById(
            UUID id, CollaboratorRepository repository) throws CollaboratorByIdNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new CollaboratorByIdNotFoundException(id));
    }
}
