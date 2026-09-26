package com.myProject.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myProject.exception.ResourceNotFoundException;
import com.myProject.user.dto.NewUserRequest;
import com.myProject.user.dto.UpdateUserRequest;
import com.myProject.user.dto.UserResponse;
import com.myProject.user.entity.Role;
import com.myProject.user.entity.User;
import com.myProject.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service // Service layer or Business logic
@RequiredArgsConstructor // Objects with "final" keyword get added to the constructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    
    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    @Transactional
    public UserResponse createNewUser(NewUserRequest newUserRequest) {
        User user = User.builder()
                .name(newUserRequest.getName())
                .email(newUserRequest.getEmail())
                .password(passwordEncoder.encode(newUserRequest.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponse.class);
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        user.setName(updateUserRequest.getName());
        user.setEmail(updateUserRequest.getEmail());
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponse.class);
    }

    @Override
    @Transactional
    public UserResponse updatePartialUser(Long id, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        if (updateUserRequest.getName() != null) {
            user.setName(updateUserRequest.getName());
        }
        if (updateUserRequest.getEmail() != null) {
            user.setEmail(updateUserRequest.getEmail());
        }
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponse.class);
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
}
