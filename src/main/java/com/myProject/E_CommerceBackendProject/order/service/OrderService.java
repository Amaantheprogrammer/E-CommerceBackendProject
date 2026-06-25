package com.myProject.E_CommerceBackendProject.order.service;

import java.util.List;

import com.myProject.E_CommerceBackendProject.order.dto.OrderDto;

public interface OrderService {
    // Admin 
    List<OrderDto> getAllOrders();

    OrderDto getByOrderId(Long orderId);

    List<OrderDto> getByUserId(Long userId);

}