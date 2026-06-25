package com.myProject.E_CommerceBackendProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer; // 🎯 CRITICAL: This was missing!
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disables CSRF protection for testing via Thunder Client
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/products/**", "/categories/**").permitAll() // Publicly open endpoints
                .anyRequest().authenticated() // Secured endpoints (like /users and /orders)
            ) 
            .httpBasic(Customizer.withDefaults()) // Reads standard HTTP Basic Authorization headers
            .formLogin(Customizer.withDefaults()); // Keeps default browser login interface supported

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
// Spring security - 49:27