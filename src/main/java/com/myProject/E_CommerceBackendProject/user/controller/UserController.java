package com.myProject.E_CommerceBackendProject.user.controller;

import java.util.List;

import com.myProject.E_CommerceBackendProject.user.dto.UpdateUserDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.myProject.E_CommerceBackendProject.user.dto.NewUserDto;
import com.myProject.E_CommerceBackendProject.user.dto.UserDto;
import com.myProject.E_CommerceBackendProject.user.service.UserService;

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
