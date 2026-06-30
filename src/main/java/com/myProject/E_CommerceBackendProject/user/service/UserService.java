package com.myProject.E_CommerceBackendProject.user.service;

import java.util.List;

import com.myProject.E_CommerceBackendProject.user.dto.NewUserDto;
import com.myProject.E_CommerceBackendProject.user.dto.UpdateUserDto;
import com.myProject.E_CommerceBackendProject.user.dto.UserDto;

public interface UserService {
    List<UserDto> getAllUsers();
    
    UserDto getUserById(Long id);

    UserDto createNewUser(NewUserDto newUserDto);

    UserDto updateUser(Long id, UpdateUserDto updateUserDto);

    UserDto updatePartialUser(Long id, UpdateUserDto updateUserDto);

    void deleteUserById(Long id);

    void deleteAllUsers();

    UserDto getUserByEmail(String email);
}