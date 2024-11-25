package com.humanit.authentication_api.repository;

import com.humanit.authentication_api.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUserCredentialsId(UUID id);
    void deleteByUserCredentialsId(UUID id);
}
