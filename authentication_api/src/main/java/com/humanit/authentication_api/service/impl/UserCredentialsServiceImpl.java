package com.humanit.authentication_api.service.impl;

import com.humanit.authentication_api.dto.AuthRequestDTO;
import com.humanit.authentication_api.dto.AuthResponseDTO;
import com.humanit.authentication_api.dto.RefreshTokenRequestDTO;
import com.humanit.authentication_api.exception.refreshToken.RefreshTokenExpiredException;
import com.humanit.authentication_api.exception.refreshToken.RefreshTokenNotFoundException;
import com.humanit.authentication_api.exception.userCredentials.UserCredentialsNotAuthenticatedException;
import com.humanit.authentication_api.model.RefreshToken;
import com.humanit.authentication_api.jwt.JwtService;
import com.humanit.authentication_api.model.UserCredentials;
import com.humanit.authentication_api.repository.UserCredentialsRepository;
import com.humanit.authentication_api.service.RefreshTokenService;
import com.humanit.authentication_api.service.UserCredentialsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCredentialsServiceImpl implements UserCredentialsService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshService;
    private final UserCredentialsRepository userRepository;

    @Override
    @Transactional
    public AuthResponseDTO signIn(AuthRequestDTO authRequestDTO) throws UserCredentialsNotAuthenticatedException {
        log.info("Attempting to authenticate user: {}", authRequestDTO.getUsername());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            log.info("Authentication successful for user: {}", authRequestDTO.getUsername());
            UserCredentials userCredentials = userRepository.findByUsername(authRequestDTO.getUsername());
            RefreshToken refreshToken = refreshService.createRefreshToken(authRequestDTO.getUsername());
            return AuthResponseDTO.builder()
                    .accessToken(jwtService.generateToken(
                            authRequestDTO.getUsername(), userCredentials.getRole().name(), userCredentials.getEmail()
                    , userCredentials.getId()))
                    .refreshToken(refreshToken.getToken())
                    .build();
        } else {
            log.error("Authentication failed for user: {}", authRequestDTO.getUsername());
            throw new UserCredentialsNotAuthenticatedException(authRequestDTO.getUsername());
        }
    }

    @Override
    @Transactional
    public AuthResponseDTO refreshToken(RefreshTokenRequestDTO refreshRequestDTO) throws RefreshTokenNotFoundException {
        log.info("Refreshing token: {}", refreshRequestDTO.getToken());
        return refreshService.findByToken(refreshRequestDTO.getToken())
                .map(token -> {
                    try {
                        return refreshService.verifyExpiration(token);
                    } catch (RefreshTokenExpiredException e) {
                        log.error("Refresh token expired: {}", token.getToken());
                        throw new RuntimeException(e);
                    }
                })
                .map(RefreshToken::getUserCredentials)
                .map(userCredentials -> {
                    log.info("Generating new access token for user: {}", userCredentials.getUsername());
                    String accessToken = jwtService.generateToken(
                            userCredentials.getUsername(), userCredentials.getRole().name(), userCredentials.getEmail(),
                            userCredentials.getId());
                    log.info("New access token generated for user: {}", userCredentials.getUsername());
                    return AuthResponseDTO.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshRequestDTO.getToken()).build();
                }).orElseThrow(() -> new RefreshTokenNotFoundException(refreshRequestDTO.getToken()));
    }
}
