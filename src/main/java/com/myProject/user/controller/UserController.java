package com.myProject.user.controller;

import java.util.List;

import com.myProject.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.myProject.user.dto.UpdateUserRequest;
import com.myProject.user.dto.UserResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor // Objects with "final" keyword get added to the constructor
@RequestMapping("/users") // Reduces the effort of writing "/users" again and again
public class UserController {

    private final UserService userService;
    
    @GetMapping
    public ResponseEntity<?> getUsers(
            @RequestParam(value = "email", required = false) String email
    ) {
        if (email != null && !email.isBlank()) {
            return ResponseEntity.ok(userService.getUserByEmail(email));
        }
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(userService.updateUser(id, updateUserRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
