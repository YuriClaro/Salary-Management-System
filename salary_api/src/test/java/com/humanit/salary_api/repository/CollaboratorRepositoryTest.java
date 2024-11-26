package com.humanit.salary_api.repository;

import com.humanit.salary_api.enumerator.Position;
import com.humanit.salary_api.model.Collaborator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Rollback
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CollaboratorRepositoryTest {
    private Collaborator collaborator;

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @BeforeEach
    public void setUp() {
        collaborator = Collaborator.builder()
                .name("Collaborator Test")
                .email("email@test.com")
                .position(Position.MANAGER)
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Test 1: Save Collaborator")
    public void SaveAll_ReturnCollaborator() {
        Collaborator savedCollaborator = collaboratorRepository.save(collaborator);

        Assertions.assertThat(savedCollaborator).isNotNull();
        Assertions.assertThat(savedCollaborator.getId()).isNotNull();
        Assertions.assertThat(savedCollaborator.getName()).isEqualTo("Collaborator Test");
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Find Collaborator by ID")
    public void testFindCollaboratorById() {
        Collaborator savedCollaborator = collaboratorRepository.save(collaborator);

        Optional<Collaborator> foundCollaborator = collaboratorRepository.findById(savedCollaborator.getId());

        Assertions.assertThat(foundCollaborator).isPresent();
        Assertions.assertThat(foundCollaborator.get().getName()).isEqualTo("Collaborator Test");
    }
}
