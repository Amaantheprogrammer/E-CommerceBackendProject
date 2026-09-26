package com.myProject.auth.service;

import com.myProject.auth.dto.SignupRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myProject.auth.dto.AuthResponse;
import com.myProject.auth.dto.SignInRequest;
import com.myProject.security.jwt.JwtService;
import com.myProject.user.entity.Role;
import com.myProject.user.entity.User;
import com.myProject.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthResponse signIn(SignInRequest request) {
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

    @Transactional
    public void signUp(SignupRequest request) {
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
