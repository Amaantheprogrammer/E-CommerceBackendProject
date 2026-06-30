package com.myProject.E_CommerceBackendProject.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    @NotBlank(message = "Email is a required field")
    private String email;
    @NotBlank(message = "Password is a required field")
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;
}