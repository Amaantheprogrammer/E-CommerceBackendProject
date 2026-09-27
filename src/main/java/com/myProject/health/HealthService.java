package com.myProject.health;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service 
public class HealthService {
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'SELLER')")
    public String healthCheck() {
        return "E-Commerce Backend Application is running";
    }
}
