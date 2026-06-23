package com.myProject.E_CommerceBackendProject.cart.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.myProject.E_CommerceBackendProject.cart.dto.CartDto;
import com.myProject.E_CommerceBackendProject.cart.dto.CartItemDto;
import com.myProject.E_CommerceBackendProject.cart.entity.Cart;
import com.myProject.E_CommerceBackendProject.cart.entity.CartItem;
import com.myProject.E_CommerceBackendProject.cart.repository.CartRepository;
import com.myProject.E_CommerceBackendProject.exception.BadRequestException;
import com.myProject.E_CommerceBackendProject.exception.ResourceNotFoundException;
import com.myProject.E_CommerceBackendProject.product.entity.Product;
import com.myProject.E_CommerceBackendProject.product.repository.ProductRepository;
import com.myProject.E_CommerceBackendProject.user.entity.User;
import com.myProject.E_CommerceBackendProject.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    @Override
    public CartDto getCartByUserId(Long userId) {
        User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        Cart cart = user.getCart();
        if (cart == null) {
            cart = Cart.builder().user(user).build();
            cart = cartRepository.save(cart);
        }
        return mapToDto(cart);
    }

    @Override
    @Transactional
    public CartDto addProductToCart(Long userId, Long productId, Integer quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
        Cart cart = user.getCart();
        if (cart == null) {
            cart = Cart.builder().user(user).build();
            user.setCart(cart);
        }
        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();
        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            int newQuantity = item.getQuantity() + quantity; 
            if (newQuantity <= 0) {
                cart.getCartItems().remove(item);
            } else {
                if (product.getStockQuantity() < newQuantity) {
                    throw new BadRequestException("Insufficient stock for product: " + product.getName());
                }
                item.setQuantity(newQuantity);
            }
        } else {
            if (quantity <= 0) {
                throw new BadRequestException("Initial product quantity must be greater than zero.");
            }
            if (product.getStockQuantity() < quantity) {
                throw new BadRequestException("Insufficient stock for product: " + product.getName());
            }
            CartItem newItem = CartItem.builder()
                    .product(product)
                    .cart(cart)
                    .quantity(quantity)
                    .build();
            cart.getCartItems().add(newItem);
        }
        return mapToDto(cartRepository.save(cart));
    }

    @Override
    @Transactional
    public void removeProductFromCart(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        // Check if product exists
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product not found with ID: " + productId);
        }
        Cart cart = user.getCart();
        if (cart == null) {
            cart = Cart.builder().user(user).build();
            user.setCart(cart);
        }
        Optional<CartItem> itemToRemove = cart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();
        if (itemToRemove.isPresent()) {
            cart.getCartItems().remove(itemToRemove.get());
        } else {
            throw new ResourceNotFoundException("Product not found with ID: " + productId);
        }
        cartRepository.save(cart);
    }
    
    // Conversion to DTO (Data Transfer Object)
    private CartItemDto cartItemToDto(CartItem cartItem) {
        return CartItemDto.builder()
                .id(cartItem.getId())
                .cartId(cartItem.getCart().getId())
                .productId(cartItem.getProduct().getId())
                .productName(cartItem.getProduct().getName())
                .productPrice(cartItem.getProduct().getPrice())
                .quantity(cartItem.getQuantity())
                .subtotal(cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .build();
    }

    private CartDto mapToDto(Cart cart) {
        List<CartItemDto> cartItemDtos = cart.getCartItems().stream().map(this::cartItemToDto).toList();

        BigDecimal total = cartItemDtos.stream()
                                    .map(CartItemDto::getSubtotal)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        return CartDto.builder()
                    .id(cart.getId())
                    .userId(cart.getUser().getId())
                    .userName(cart.getUser().getName())
                    .cartItems(cartItemDtos)
                    .total(total)
                    .build();
    }

}