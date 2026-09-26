package com.myProject.auth.controller;

import com.myProject.auth.dto.SignupRequest;
import com.myProject.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.auth.dto.AuthResponse;
import com.myProject.auth.dto.SignInRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    
    private final AuthService authService;

    // Sign-in
    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authService.signIn(request));
    }
    
    // Sign-up
    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody SignupRequest request) {
        authService.signUp(request);
        return ResponseEntity.noContent().build();
    }
}
