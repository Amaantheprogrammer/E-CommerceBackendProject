package com.myProject.order.service;

import java.util.List;

import com.myProject.order.dto.OrderResponse;
import com.myProject.order.dto.OrderRequest;
import com.myProject.order.entity.OrderStatus;
import com.myProject.order.entity.PaymentStatus;

public interface OrderService {

    List<OrderResponse> getAllOrders();

    OrderResponse getByOrderId(Long orderId);

    List<OrderResponse> getByUserId(Long userId);

    OrderResponse updateOrderStatus(Long orderId, OrderStatus orderStatus);

    OrderResponse updatePaymentStatus(Long orderid, PaymentStatus paymentStatus);

    OrderResponse placeOrder(OrderRequest orderRequest);

    void cancelOrder(Long orderId);

}