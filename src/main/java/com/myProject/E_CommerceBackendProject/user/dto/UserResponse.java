package com.myProject.E_CommerceBackendProject.user.dto;

import com.myProject.E_CommerceBackendProject.user.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder 
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Role role;
}