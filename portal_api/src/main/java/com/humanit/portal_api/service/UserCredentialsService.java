package com.humanit.portal_api.service;

import com.humanit.portal_api.dto.authorization.AuthRequestDTO;
import com.humanit.portal_api.dto.authorization.AuthResponseDTO;
import com.humanit.portal_api.dto.authorization.RefreshTokenRequestDTO;

public interface UserCredentialsService {
    AuthResponseDTO signIn(AuthRequestDTO authRequestDTO);
    AuthResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
}
