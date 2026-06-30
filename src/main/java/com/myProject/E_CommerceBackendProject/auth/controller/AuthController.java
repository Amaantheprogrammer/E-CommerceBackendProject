package com.myProject.E_CommerceBackendProject.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.E_CommerceBackendProject.auth.dto.AuthResponse;
import com.myProject.E_CommerceBackendProject.auth.dto.LoginRequest;
import com.myProject.E_CommerceBackendProject.auth.service.AuthService;
import com.myProject.E_CommerceBackendProject.user.dto.NewUserDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    
    private final AuthService authService;

    // Login 
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
    
    // Register
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody NewUserDto request) {
        authService.register(request);
        return ResponseEntity.noContent().build();
    }
}
