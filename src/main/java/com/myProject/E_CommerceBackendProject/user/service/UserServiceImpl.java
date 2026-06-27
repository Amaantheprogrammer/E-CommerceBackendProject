package com.myProject.E_CommerceBackendProject.user.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myProject.E_CommerceBackendProject.exception.ResourceNotFoundException;
import com.myProject.E_CommerceBackendProject.user.dto.NewUserDto;
import com.myProject.E_CommerceBackendProject.user.dto.UpdateUserDto;
import com.myProject.E_CommerceBackendProject.user.dto.UserDto;
import com.myProject.E_CommerceBackendProject.user.entity.Role;
import com.myProject.E_CommerceBackendProject.user.entity.User;
import com.myProject.E_CommerceBackendProject.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service // Service layer or Business logic
@RequiredArgsConstructor // Objects with "final" keyword get added to the constructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapToDto).toList();
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        return mapToDto(user);
    }

    @Override
    @Transactional
    public UserDto createNewUser(NewUserDto newUserDto) {
        User user = User.builder()
                .name(newUserDto.getName())
                .email(newUserDto.getEmail())
                .password(passwordEncoder.encode(newUserDto.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        return mapToDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, UpdateUserDto updateUserDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        user.setName(updateUserDto.getName());
        user.setEmail(updateUserDto.getEmail());
        return mapToDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserDto updatePartialUser(Long id, UpdateUserDto updateUserDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        if (updateUserDto.getName() != null) {
            user.setName(updateUserDto.getName());
        }
        if (updateUserDto.getEmail() != null) {
            user.setEmail(updateUserDto.getEmail());
        }
        return mapToDto(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllUsers() {
        if (userRepository.count() == 0) {
            throw new ResourceNotFoundException("Database is empty");
        }
        userRepository.deleteAll();
    }

    // Map to DTO
    private UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
