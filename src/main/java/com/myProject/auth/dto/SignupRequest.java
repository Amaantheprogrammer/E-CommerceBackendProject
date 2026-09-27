package com.myProject.auth.dto;

import com.myProject.user.entity.PaymentMethod;

import com.myProject.user.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignupRequest {
    @NotBlank(message = "Name is a required field")
    private String name;

    @NotBlank(message = "Email is a required field")
    @Email
    private String email;

    @NotBlank(message = "Password is a required field")
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;

    @NotBlank(message = "Payment method is a required field")
    private PaymentMethod paymentMethod;

    @NotBlank(message = "Role is a required field")
    private Role role;
}
