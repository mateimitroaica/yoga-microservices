package org.unibuc.authservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibuc.authservice.dto.AuthRequest;
import org.unibuc.authservice.dto.AuthResponse;
import org.unibuc.authservice.dto.RegisterRequest;
import org.unibuc.authservice.service.AuthService;
import org.unibuc.authservice.service.TokenBlacklistService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final TokenBlacklistService tokenBlacklistService;

    public AuthController(AuthService authService, TokenBlacklistService tokenBlacklistService) {
        this.authService = authService;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            authService.logout(token);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/token/blacklisted")
    public ResponseEntity<Boolean> isBlacklisted(@RequestParam String token) {
        boolean isBlacklisted = tokenBlacklistService.isBlacklisted(token);
        return ResponseEntity.ok(isBlacklisted);
    }
}
