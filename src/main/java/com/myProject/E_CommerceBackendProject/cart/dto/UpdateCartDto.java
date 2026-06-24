package com.myProject.E_CommerceBackendProject.cart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCartDto {
    @NotNull(message = "User ID is a required field")
    private Long userId;

    @NotNull(message = "Product ID is a required field")
    private Long productId;
    
    @NotNull(message = "Quantity is a requied field")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}