package com.myProject.E_CommerceBackendProject.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.E_CommerceBackendProject.user.dto.NewUserDto;
import com.myProject.E_CommerceBackendProject.user.dto.UpdateUserDto;
import com.myProject.E_CommerceBackendProject.user.dto.UserDto;
import com.myProject.E_CommerceBackendProject.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor // Objects with "final" keyword get added to the constructor
@RequestMapping("/users") // Reduces the effort of writing "/users" again and again
public class UserController {

    private final UserService userService;
    
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> createNewUser(@RequestBody @Valid NewUserDto newUserDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createNewUser(newUserDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(userService.updateUser(id, updateUserDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updatePartialUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(userService.updatePartialUser(id, updateUserDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.noContent().build();
    }
}