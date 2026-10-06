package com.myProject.service;

import com.myProject.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    void testFindByEmail() {
        assertTrue(userRepository.existsByEmail("admin1@ecommerce.com"));
    }
}
