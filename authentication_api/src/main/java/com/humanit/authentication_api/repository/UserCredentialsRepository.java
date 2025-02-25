package com.humanit.authentication_api.repository;

import com.humanit.authentication_api.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, UUID> {
    UserCredentials findByUsername(String username);
    Optional<UserCredentials> findById(UUID id);
}
