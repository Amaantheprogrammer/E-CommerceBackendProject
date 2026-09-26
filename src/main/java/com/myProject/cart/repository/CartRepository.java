package com.myProject.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.myProject.cart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    
    Optional<Cart> findByUser_Id(@Param("userId") Long userId);
    
}