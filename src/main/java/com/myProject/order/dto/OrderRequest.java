package com.myProject.E_CommerceBackendProject.order.dto;

import com.myProject.E_CommerceBackendProject.order.entity.PaymentMethod;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    @NotNull(message = "Product ID is required")
    private Long productId;
    @NotNull(message = "Quantity is required")
    private Integer quantity;
    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;
}