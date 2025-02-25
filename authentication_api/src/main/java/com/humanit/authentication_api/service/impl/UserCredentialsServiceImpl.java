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
import com.humanit.authentication_api.repository.RefreshTokenRepository;
import com.humanit.authentication_api.repository.UserCredentialsRepository;
import com.humanit.authentication_api.service.RefreshTokenService;
import com.humanit.authentication_api.service.TokenBlacklistService;
import com.humanit.authentication_api.service.UserCredentialsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.humanit.authentication_api.enumerator.UserCredentialsStatus.LOGGED_IN;
import static com.humanit.authentication_api.enumerator.UserCredentialsStatus.LOGGED_OUT;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCredentialsServiceImpl implements UserCredentialsService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshService;
    private final UserCredentialsRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenBlacklistService tokenBlacklistService;

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
            log.info("User: {} is now logged in", authRequestDTO.getUsername());
            userCredentials.setStatus(LOGGED_IN);
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

    @Override
    @Transactional
    public void logout(HttpServletRequest request) {
        log.info("Starting process to logout user.");
        String token = request.getHeader("Authorization").substring(7);
        UUID id = jwtService.extractId(token);
        Optional<UserCredentials> user = userRepository.findById(id);
        UserCredentials existingUser = user.get();

        existingUser.setStatus(LOGGED_OUT);
        log.info("User: {} is now logged out", jwtService.extractUsername(token));
        refreshTokenRepository.deleteByUserCredentialsId(id);
        tokenBlacklistService.addBlacklist(token);

        userRepository.save(existingUser);
        SecurityContextHolder.clearContext();
        log.info("User logout successfully");
    }
}
