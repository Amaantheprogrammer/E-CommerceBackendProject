package com.myProject.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myProject.cart.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}