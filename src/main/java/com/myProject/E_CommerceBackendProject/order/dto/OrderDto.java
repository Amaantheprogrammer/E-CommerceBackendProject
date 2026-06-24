package com.myProject.E_CommerceBackendProject.order.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.myProject.E_CommerceBackendProject.order.entity.OrderItem;
import com.myProject.E_CommerceBackendProject.order.entity.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {  
    private Long id;
    private Long userId;
    private String userName;
    private List<OrderItem> orderItems;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private LocalDateTime orderDate;
}