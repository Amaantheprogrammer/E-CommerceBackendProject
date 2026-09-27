package com.myProject.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor
public class HealthController {
    private final HealthService healthService;
    @GetMapping("/")
    public String healthCheck() {
        return healthService.healthCheck();
    }
    
}
