package com.humanit.authentication_api.service;

import com.humanit.authentication_api.dto.AuthRequestDTO;
import com.humanit.authentication_api.dto.AuthResponseDTO;
import com.humanit.authentication_api.dto.RefreshTokenRequestDTO;
import com.humanit.authentication_api.exception.refreshToken.RefreshTokenNotFoundException;
import com.humanit.authentication_api.exception.userCredentials.UserCredentialsNotAuthenticatedException;
import jakarta.servlet.http.HttpServletRequest;

public interface UserCredentialsService {
    AuthResponseDTO signIn(AuthRequestDTO authRequestDTO) throws UserCredentialsNotAuthenticatedException;
    AuthResponseDTO refreshToken(RefreshTokenRequestDTO refreshRequestDTO) throws RefreshTokenNotFoundException;
    void logout(HttpServletRequest request);
}
