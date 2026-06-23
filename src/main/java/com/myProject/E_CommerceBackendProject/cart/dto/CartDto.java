package com.myProject.E_CommerceBackendProject.cart.dto;

import java.math.BigDecimal;
import java.util.List;

import com.myProject.E_CommerceBackendProject.cart.dto.CartItemDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    private Long id;
    private Long userId;
    private String userName;
    private List<CartItemDto> cartItems;
    private BigDecimal total;
}