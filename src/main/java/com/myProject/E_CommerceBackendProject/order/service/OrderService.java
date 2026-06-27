package com.myProject.E_CommerceBackendProject.order.service;

import java.util.List;

import com.myProject.E_CommerceBackendProject.order.dto.OrderDto;
import com.myProject.E_CommerceBackendProject.order.dto.OrderRequest;
import com.myProject.E_CommerceBackendProject.order.entity.OrderStatus;
import com.myProject.E_CommerceBackendProject.order.entity.PaymentStatus;

public interface OrderService {

    List<OrderDto> getAllOrders();

    OrderDto getByOrderId(Long orderId);

    List<OrderDto> getByUserId(Long userId);

    OrderDto updateOrderStatus(Long orderId, OrderStatus orderStatus);

    OrderDto updatePaymentStatus(Long orderid, PaymentStatus paymentStatus);

    OrderDto placeOrder(Long userId, OrderRequest orderRequest);

    void cancelOrder(Long orderId);

}