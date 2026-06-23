package com.myProject.E_CommerceBackendProject.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddToCartDto {
    private Long userId;
    private Long productId;
    private Integer quantity;
}