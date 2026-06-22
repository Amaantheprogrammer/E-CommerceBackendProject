package com.myProject.E_CommerceBackendProject.cart.service;

import com.myProject.E_CommerceBackendProject.product.entity.Product;

public interface CartService {
    void addProduct(Product product);

    void removeProduct(Product product);
}