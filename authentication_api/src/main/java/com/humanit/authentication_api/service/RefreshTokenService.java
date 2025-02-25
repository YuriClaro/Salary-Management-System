package com.humanit.authentication_api.service;

import com.humanit.authentication_api.exception.refreshToken.RefreshTokenExpiredException;
import com.humanit.authentication_api.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(String username);
    Optional<RefreshToken> findByToken(String token);
    RefreshToken verifyExpiration(RefreshToken token) throws RefreshTokenExpiredException;
}
