package com.humanit.portal_api.client;

import com.humanit.portal_api.dto.authorization.AuthRequestDTO;
import com.humanit.portal_api.dto.authorization.AuthResponseDTO;
import com.humanit.portal_api.dto.authorization.RefreshTokenRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "userCredentialsFeignClient", url = "${microservice.authentication_api}")
public interface UserCredentialsFeignClient {
    @PostMapping("/signIn")
    ResponseEntity<AuthResponseDTO> signIn(@RequestBody AuthRequestDTO authRequestDTO);

    @PostMapping("/refreshToken")
    ResponseEntity<AuthResponseDTO> refreshToken(@RequestBody RefreshTokenRequestDTO refreshRequestDTO);

    @PostMapping("/logout")
    ResponseEntity<Void> logout(@RequestHeader("Authorization") String authHeader);
}
