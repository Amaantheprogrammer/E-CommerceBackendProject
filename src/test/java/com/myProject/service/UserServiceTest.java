package com.myProject.service;

import com.myProject.user.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Disabled
    @Test
    void testFindByEmail() {
        assertTrue(userRepository.existsByEmail("admin@ecommerce.com"));
    }

    @ParameterizedTest
    @CsvSource({
            "2, 1, 1",
            "5, 3, 2"
    })
    void test(Integer expected, Integer a, Integer b) {
        assertEquals(expected, a + b);
    }
}
