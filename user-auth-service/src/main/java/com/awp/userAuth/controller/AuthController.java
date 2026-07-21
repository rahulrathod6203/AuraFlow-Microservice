package com.awp.userAuth.controller;

import com.awp.userAuth.dto.AuthResponse;
import com.awp.userAuth.dto.LoginRequest;
import com.awp.userAuth.dto.RegisterRequest;
import com.awp.userAuth.service.UserAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("http://localhost:5173/*")
public class AuthController {

    private final UserAuthService userAuthService;

    @PostMapping(value = {"/login", "/sign-in"})
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        AuthResponse successResponse = userAuthService.login(loginRequest);
        return ResponseEntity.ok(successResponse);
    }

    @PostMapping(value = {"/register", "/sign-up"})
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        AuthResponse authResponse = userAuthService.register(registerRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{email}")
                .buildAndExpand(registerRequest.email())
                .toUri();
        return ResponseEntity.created(location).body(authResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        log.info("Processing logout request...");
        userAuthService.logout();
        return ResponseEntity.noContent().build();
    }
}