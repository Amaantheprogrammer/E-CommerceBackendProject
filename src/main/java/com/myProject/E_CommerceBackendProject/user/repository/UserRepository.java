package com.myProject.E_CommerceBackendProject.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myProject.E_CommerceBackendProject.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Create own method besides the in-built ones(findAll, findById, deleteAll, etc)
    // Refer for possible methods: https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    List<User> findByName(String name); // Works automatically by searching through the entity in camel case

    Optional<User> findByEmail(String email);
    
    boolean existsByEmail(String email);
}