package com.myProject.E_CommerceBackendProject.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myProject.E_CommerceBackendProject.cart.entity.Cart;

public interface CartItemRepository extends JpaRepository<Cart, Long> {

}