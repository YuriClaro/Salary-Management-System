package com.humanit.portal_api.controller;

import com.humanit.portal_api.dto.authorization.AuthRequestDTO;
import com.humanit.portal_api.dto.authorization.AuthResponseDTO;
import com.humanit.portal_api.dto.authorization.RefreshTokenRequestDTO;
import com.humanit.portal_api.service.UserCredentialsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/portal/auth")
@RequiredArgsConstructor
public class UserCredentialsController {
    private final UserCredentialsService userService;

    @Operation(
            summary = "Sign in a user",
            description = "Authenticates a user with the provided credentials and returns an authentication token."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User signed in successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid credentials provided"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access")
    })
    @PostMapping("/signIn")
    public ResponseEntity<AuthResponseDTO> signIn(@RequestBody AuthRequestDTO authRequestDTO) {
        return ResponseEntity.ok(userService.signIn(authRequestDTO));
    }

    @Operation(
            summary = "Refresh authentication token",
            description = "Refreshes the authentication token for a user based on the provided refresh token."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token refreshed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid refresh token"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access")
    })
    @PostMapping("/refreshToken")
    public ResponseEntity<AuthResponseDTO> refreshToken(@RequestBody RefreshTokenRequestDTO refreshRequestDTO) {
        return ResponseEntity.ok(userService.refreshToken(refreshRequestDTO));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        userService.logout(request);
        return ResponseEntity.noContent().build();
    }
}
