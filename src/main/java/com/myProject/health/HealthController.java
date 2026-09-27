package com.myProject.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class HealthController {
    @GetMapping("/")
    public String healthCheck() {
        return "E-Commerce Backend Application is running";
    }
    
}
