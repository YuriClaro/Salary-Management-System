package com.humanit.salary_api.validator;

import com.humanit.salary_api.exception.collaborator.CollaboratorByIdNotFoundException;
import com.humanit.salary_api.exception.collaborator.InvalidCollaboratorException;
import com.humanit.salary_api.model.Collaborator;
import com.humanit.salary_api.repository.CollaboratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CollaboratorValidator {
    public static Collaborator findCollaboratorById(
            UUID id, CollaboratorRepository collaboratorRepository) throws CollaboratorByIdNotFoundException {
        return collaboratorRepository.findById(id)
                .orElseThrow(() -> new CollaboratorByIdNotFoundException(id));
    }

    public static Collaborator findCollaboratorByEmail(
            String email, CollaboratorRepository collaboratorRepository) throws InvalidCollaboratorException {
        return collaboratorRepository.findByEmail(email)
                .orElseThrow(InvalidCollaboratorException::new);
    }

}
