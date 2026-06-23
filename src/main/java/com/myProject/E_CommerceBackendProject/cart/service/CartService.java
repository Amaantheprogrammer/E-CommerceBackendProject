package com.myProject.E_CommerceBackendProject.cart.service;

import com.myProject.E_CommerceBackendProject.cart.dto.CartDto;

public interface CartService {

    CartDto getCartByUserId(Long userId);

    CartDto addProductToCart(Long userId, Long productId, Integer quantity);

    void removeProductFromCart(Long userId, Long productId);
    
}