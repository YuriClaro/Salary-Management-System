package com.humanit.portal_api.service;

import com.humanit.portal_api.dto.authorization.AuthRequestDTO;
import com.humanit.portal_api.dto.authorization.AuthResponseDTO;
import com.humanit.portal_api.dto.authorization.RefreshTokenRequestDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface UserCredentialsService {
    AuthResponseDTO signIn(AuthRequestDTO authRequestDTO);
    AuthResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
    void logout(HttpServletRequest request);
}
