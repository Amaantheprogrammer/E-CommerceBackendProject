package com.myProject.E_CommerceBackendProject.security.dto;

import jakarta.validation.constraints.Email;    
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {
    
    @NotBlank(message = "Email is a required field")
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotBlank(message = "Password is a required field")
    private String password;

}