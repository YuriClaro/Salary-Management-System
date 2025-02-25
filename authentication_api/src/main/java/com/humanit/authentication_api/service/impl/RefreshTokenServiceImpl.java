package com.humanit.authentication_api.service.impl;

import com.humanit.authentication_api.exception.refreshToken.RefreshTokenExpiredException;
import com.humanit.authentication_api.model.RefreshToken;
import com.humanit.authentication_api.model.UserCredentials;
import com.humanit.authentication_api.repository.RefreshTokenRepository;
import com.humanit.authentication_api.repository.UserCredentialsRepository;
import com.humanit.authentication_api.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshRepository;
    private final UserCredentialsRepository userRepository;

    @Override
    public RefreshToken createRefreshToken(String username) {
        UserCredentials userCredentials = userRepository.findByUsername(username);
        Optional<RefreshToken> existingToken = refreshRepository.findByUserCredentialsId(userCredentials.getId());
        RefreshToken refreshToken;
        if (existingToken.isPresent()) {
            refreshToken = existingToken.get();
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setExpiryDate(Instant.now().plusMillis(600000));
        } else {
            log.info("Creating refresh token for username: {}", username);
            refreshToken = RefreshToken.builder()
                    .userCredentials(userCredentials)
                    .token(UUID.randomUUID().toString())
                    .expiryDate(Instant.now().plusMillis(600000)) // set expiry of refresh token to 10min
                    .build();
        }
        log.info("Refresh token created successfully for username: {}, refresh token: {}", username, refreshToken);
        return refreshRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshRepository.findByToken(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) throws RefreshTokenExpiredException {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshRepository.delete(token);
            throw new RefreshTokenExpiredException();
        }
        return token;
    }
}
