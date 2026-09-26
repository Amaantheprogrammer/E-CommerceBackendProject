package com.myProject.E_CommerceBackendProject.order.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.myProject.E_CommerceBackendProject.order.entity.OrderStatus;
import com.myProject.E_CommerceBackendProject.order.entity.PaymentMethod;
import com.myProject.E_CommerceBackendProject.order.entity.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private Long userId;
    private String userName;
    private List<OrderItemResponse> orderItems;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
}