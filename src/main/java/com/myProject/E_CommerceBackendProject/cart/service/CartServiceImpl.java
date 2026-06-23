package com.myProject.E_CommerceBackendProject.cart.service;

import org.springframework.stereotype.Service;

import com.myProject.E_CommerceBackendProject.cart.dto.CartItemDto;
import com.myProject.E_CommerceBackendProject.cart.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public CartItemDto addToCart(CartItemDto cartItemDto) {

        return null;
    }

    @Override
    public void removeFromCart(CartItemDto cartItemDto) {
        
    }

   
}