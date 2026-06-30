package com.myProject.E_CommerceBackendProject.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myProject.E_CommerceBackendProject.auth.dto.AuthResponse;
import com.myProject.E_CommerceBackendProject.auth.dto.LoginRequest;
import com.myProject.E_CommerceBackendProject.security.jwt.JwtService;
import com.myProject.E_CommerceBackendProject.user.dto.NewUserDto;
import com.myProject.E_CommerceBackendProject.user.entity.Role;
import com.myProject.E_CommerceBackendProject.user.entity.User;
import com.myProject.E_CommerceBackendProject.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(
                "User not found with email: " + request.getEmail()));

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole().name());

        return new AuthResponse(token);
    }

    @Override
    @Transactional
    public void register(NewUserDto request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .paymentMethod(request.getPaymentMethod())
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);
    }

}
