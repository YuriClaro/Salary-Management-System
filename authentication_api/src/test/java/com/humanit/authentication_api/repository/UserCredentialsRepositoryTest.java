package com.humanit.authentication_api.repository;

import com.humanit.authentication_api.enumerator.UserCredentialsRole;
import com.humanit.authentication_api.model.UserCredentials;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserCredentialsRepositoryTest {

    @Autowired
    private UserCredentialsRepository userRepository;

    @Test
    @DisplayName("Test 1: Find User Credentials by Username Test")
    @Order(1)
    public void UserRepository_FindByUsername_ReturnUsername() {
        UserCredentials userCredentials = UserCredentials.builder()
                .username("userTest")
                .email("email@test.com")
                .role(UserCredentialsRole.ADMIN)
                .password("1234")
                .build();

        userRepository.save(userCredentials);

        UserCredentials userReturned = userRepository.findByUsername(userCredentials.getUsername());

        Assertions.assertThat(userReturned).isNotNull();
        Assertions.assertThat(userReturned.getUsername()).isEqualTo(userCredentials.getUsername());
        Assertions.assertThat(userReturned.getEmail()).isEqualTo(userCredentials.getEmail());
        Assertions.assertThat(userReturned.getRole()).isEqualTo(userCredentials.getRole());
    }
}
