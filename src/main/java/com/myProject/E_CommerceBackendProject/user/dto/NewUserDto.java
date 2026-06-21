// For User creation
package com.myProject.E_CommerceBackendProject.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewUserDto {
    @NotBlank(message = "Name is a required field")
    private String name;

    @NotBlank(message = "Email is a required field")
    @Email
    private String email;

    @NotBlank(message = "Password is a required field")
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;
}