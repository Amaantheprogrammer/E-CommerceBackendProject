package com.myProject.auth.service;

import com.myProject.auth.dto.AuthResponse;
import com.myProject.auth.dto.LoginRequest;
import com.myProject.user.dto.NewUserRequest;

public interface AuthService {
    
    AuthResponse login(LoginRequest request);
    
    void register(NewUserRequest request);
    
}