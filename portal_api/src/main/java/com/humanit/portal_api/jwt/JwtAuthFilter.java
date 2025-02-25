package com.humanit.portal_api.jwt;

import com.humanit.portal_api.exceptions.jwt.InvalidAuthorizationHeaderException;
import com.humanit.portal_api.exceptions.jwt.InvalidTokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    private final String[] WHITELIST = {
            "/api/v1/portal/auth/signIn",
            "/api/v1/portal/auth/refreshToken",
            "/swagger-ui/",
            "/v3/api-docs/",
            "/v3/api-docs",
            "/swagger-resources/"
    };

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        for (String whiteList : WHITELIST) {
            if (requestURI.startsWith(whiteList)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        String authHeader = request.getHeader("Authorization");
        log.info("Validating header to: {}", request.getRequestURI());
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidAuthorizationHeaderException();
        }

        String token = authHeader.substring(7);
        if (!jwtService.validateToken(token)) {
            throw new InvalidTokenException();
        }

        String username = jwtService.extractUsername(token);
        List<SimpleGrantedAuthority> authorities = jwtService.extractAuthorities(token);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
