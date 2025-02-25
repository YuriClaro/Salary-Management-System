package com.humanit.authentication_api.service.impl;

import com.humanit.authentication_api.config.CustomUserDetails;
import com.humanit.authentication_api.model.UserCredentials;
import com.humanit.authentication_api.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserCredentialsRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Starting user authentication...");
        UserCredentials userCredentials = userRepository.findByUsername(username);

        if(userCredentials == null) {
            log.info("Username not found: " + username);
            throw new UsernameNotFoundException("Could not found user.");
        }

        return new CustomUserDetails(userCredentials);
    }
}
