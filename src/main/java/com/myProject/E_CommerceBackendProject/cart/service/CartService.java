package com.myProject.E_CommerceBackendProject.cart.service;

import com.myProject.E_CommerceBackendProject.cart.dto.CartDto;

public interface CartService {

    CartDto getCartByUserId(Long userId);

    CartDto updateItemQuantity(Long userId, Long productId, Integer quantity);

    CartDto removeProductFromCart(Long userId, Long productId);
    
    void clearCart(Long userId);
}