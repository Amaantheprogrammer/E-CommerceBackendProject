// Generates and Validates tokens
package com.myProject.E_CommerceBackendProject.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    private static final String SECRET_KEY = "3cfa76ef5246212458a2307ef6901a5f6e804f3263725b83cfcd091ea285fef1";
    private static final long JWT_EXPIRATION = 1000 * 60 * 60; // 1 hour

    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 3600))
            .addClaims(new HashMap<>())
            .signWith(getSignedKey(), SignatureAlgorithm.HS256)
            .compact();
    }
    
    private Key getSignedKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}

// Continue from here 