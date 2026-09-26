package com.myProject.user.dto;

import com.myProject.user.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder 
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Role role;
}