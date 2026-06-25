package com.myProject.E_CommerceBackendProject.order.dto;

import com.myProject.E_CommerceBackendProject.order.entity.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private Long productId;
    private Integer quantity;
    private PaymentMethod paymentMethod;
}