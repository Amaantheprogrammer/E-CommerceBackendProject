package com.myProject.E_CommerceBackendProject.cart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.E_CommerceBackendProject.cart.dto.CartDto;
import com.myProject.E_CommerceBackendProject.cart.dto.UpdateCartDto;
import com.myProject.E_CommerceBackendProject.cart.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCartByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }
    
    @PostMapping("/items")
    public ResponseEntity<CartDto> updateItemQuantity(@RequestBody UpdateCartDto request) {
        CartDto updatedCart = cartService.updateItemQuantity(request.getUserId(), request.getProductId(), request.getQuantity());
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{userId}/items/{productId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        cartService.removeProductFromCart(userId, productId);
        return ResponseEntity.noContent().build();
    }
}
