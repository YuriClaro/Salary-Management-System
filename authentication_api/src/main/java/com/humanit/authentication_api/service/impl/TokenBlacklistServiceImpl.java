package com.humanit.authentication_api.service.impl;

import com.humanit.authentication_api.service.TokenBlacklistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class TokenBlacklistServiceImpl implements TokenBlacklistService {
    private Set<String> blacklist = new HashSet<>();

    @Override
    public void addBlacklist(String token) {
        blacklist.add(token);
    }

    @Override
    public boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }
}
