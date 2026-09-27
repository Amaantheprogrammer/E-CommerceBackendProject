package com.myProject.health;

import org.springframework.stereotype.Service;

@Service 
public class HealthService {
    public String healthCheck() {
        return "E-Commerce Backend Application is running";
    }
}
