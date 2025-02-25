package com.humanit.authentication_api.service;

public interface TokenBlacklistService {
    void addBlacklist(String token);
    boolean isBlacklisted(String token);
}
