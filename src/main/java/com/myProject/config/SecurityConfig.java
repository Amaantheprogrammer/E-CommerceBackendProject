package com.myProject.E_CommerceBackendProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.myProject.E_CommerceBackendProject.security.jwt.JwtAuthFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> 
                auth // Public Endpoints
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/products/*", "/categories/**").permitAll()
                    // User Management
                    .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.POST, "/users").hasAnyRole("USER", "SELLER")
                    .requestMatchers(HttpMethod.PUT, "/users/**") .hasAnyRole("ADMIN", "USER", "SELLER")
                    .requestMatchers(HttpMethod.PATCH, "/users/**") .hasAnyRole("ADMIN", "USER", "SELLER")
                    .requestMatchers(HttpMethod.DELETE, "/users/**") .hasAnyRole("ADMIN", "USER", "SELLER")
                    .requestMatchers(HttpMethod.DELETE, "/users") .hasRole("ADMIN")
                    // Product Management
                    .requestMatchers(HttpMethod.GET, "/products").hasAnyRole("ADMIN", "USER", "SELLER")
                    .requestMatchers(HttpMethod.GET, "/products/**").hasAnyRole("ADMIN", "USER", "SELLER")
                    .requestMatchers(HttpMethod.POST, "/products") .hasRole("SELLER")
                    .requestMatchers(HttpMethod.PUT, "/products/**") .hasRole("SELLER")
                    .requestMatchers(HttpMethod.PATCH, "/products/**").hasRole("SELLER")
                    .requestMatchers(HttpMethod.DELETE, "/products/**") .hasRole("SELLER")
                    // Order Management
                    .requestMatchers(HttpMethod.GET, "/orders") .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/orders/*").hasAnyRole("ADMIN", "USER", "SELLER")
                    .requestMatchers(HttpMethod.GET, "/orders/user/*").hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.POST, "/orders/place-order").hasRole("USER")
                    .requestMatchers(HttpMethod.PATCH, "/orders/*/cancel") .hasAnyRole("ADMIN", "USER")
                    .requestMatchers(HttpMethod.PATCH, "/orders/*/order-status").hasAnyRole("ADMIN", "SELLER")
                    .requestMatchers(HttpMethod.PATCH, "/orders/*/payment-status") .hasAnyRole("ADMIN", "SELLER")
                    .anyRequest().authenticated()

            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(daoAuthenticationProvider);
    }
}