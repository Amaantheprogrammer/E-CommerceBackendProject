package com.myProject.E_CommerceBackendProject.cart.service;

import org.springframework.stereotype.Service;

import com.myProject.E_CommerceBackendProject.cart.repository.CartRepository;
import com.myProject.E_CommerceBackendProject.product.entity.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public void addProduct(Product product) {
        
    }

    @Override
    public void removeProduct(Product product) {
       
    }
}