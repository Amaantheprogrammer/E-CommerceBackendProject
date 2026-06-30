package com.myProject.E_CommerceBackendProject.order.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.E_CommerceBackendProject.order.dto.OrderDto;
import com.myProject.E_CommerceBackendProject.order.dto.OrderRequest;
import com.myProject.E_CommerceBackendProject.order.entity.OrderStatus;
import com.myProject.E_CommerceBackendProject.order.entity.PaymentStatus;
import com.myProject.E_CommerceBackendProject.order.service.OrderService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getByOrderId(orderId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDto>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getByUserId(userId));
    }

    @PatchMapping("/{orderId}/order-status")
    public ResponseEntity<OrderDto> updateOrderQStatus(@PathVariable Long orderId, @RequestBody OrderStatus orderStatus) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, orderStatus));
    }

    @PatchMapping("/{orderId}/payment-status")
    public ResponseEntity<OrderDto> updatePaymentStatus(@PathVariable Long orderId, @RequestBody PaymentStatus paymentStatus) {
        return ResponseEntity.ok(orderService.updatePaymentStatus(orderId, paymentStatus));
    }

    @PostMapping("/place-order")
    public ResponseEntity<OrderDto> placeOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.placeOrder(orderRequest));
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}