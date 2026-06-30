package com.myProject.E_CommerceBackendProject.auth.service;

import com.myProject.E_CommerceBackendProject.auth.dto.AuthResponse;
import com.myProject.E_CommerceBackendProject.auth.dto.LoginRequest;
import com.myProject.E_CommerceBackendProject.user.dto.NewUserDto;

public interface AuthService {
    
    AuthResponse login(LoginRequest request);
    
    void register(NewUserDto request);
    
}