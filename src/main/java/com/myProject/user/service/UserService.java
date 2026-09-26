package com.myProject.user.service;

import java.util.List;

import com.myProject.user.dto.NewUserRequest;
import com.myProject.user.dto.UpdateUserRequest;
import com.myProject.user.dto.UserResponse;

public interface UserService {
    List<UserResponse> getAllUsers();
    
    UserResponse getUserById(Long id);

    UserResponse createNewUser(NewUserRequest newUserRequest);

    UserResponse updateUser(Long id, UpdateUserRequest updateUserRequest);

    UserResponse updatePartialUser(Long id, UpdateUserRequest updateUserRequest);

    void deleteUserById(Long id);

    void deleteAllUsers();

    UserResponse getUserByEmail(String email);
}