package com.myProject.E_CommerceBackendProject.cart.service;

import com.myProject.E_CommerceBackendProject.cart.dto.CartItemDto;

public interface CartService {
    CartItemDto addToCart(CartItemDto cartItemDto);

    void removeFromCart(CartItemDto cartItemDto);
}