package com.humanit.portal_api.service.impl;

import com.humanit.portal_api.client.UserCredentialsFeignClient;
import com.humanit.portal_api.dto.authorization.AuthRequestDTO;
import com.humanit.portal_api.dto.authorization.AuthResponseDTO;
import com.humanit.portal_api.dto.authorization.RefreshTokenRequestDTO;
import com.humanit.portal_api.service.UserCredentialsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCredentialsServiceImpl implements UserCredentialsService {
    private final UserCredentialsFeignClient userFeignClient;

    @Override
    public AuthResponseDTO signIn(AuthRequestDTO authRequestDTO) {
        log.info("Starting sign-in process for user: {}", authRequestDTO.getUsername());
        AuthResponseDTO authResponse = userFeignClient.signIn(authRequestDTO).getBody();
        if (authResponse != null) {
            log.info("User successfully signed in. Token generated: {}", authResponse.getAccessToken());
        } else {
            log.warn("Sign-in failed for user: {}", authRequestDTO.getUsername());
        }
        return authResponse;
    }

    @Override
    public AuthResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        log.info("Starting token refresh process.");
        AuthResponseDTO refreshedAuthResponse = userFeignClient.refreshToken(refreshTokenRequestDTO).getBody();
        if (refreshedAuthResponse != null) {
            log.info("Token successfully refreshed");
        } else {
            log.warn("Token refresh failed.");
        }
        return refreshedAuthResponse;
    }

    @Override
    public void logout(HttpServletRequest request) {
        log.info("Starting logout process.");
        userFeignClient.logout(request.getHeader("Authorization")).getBody();
        if (request.getHeader("Authorization") != null) {
            log.info("User logout successfully");
        } else {
            log.warn("Logout failed for user.");
        }
    }
}
