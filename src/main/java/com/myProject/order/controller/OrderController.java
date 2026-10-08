package com.myProject.order.controller;

import java.util.List;

import com.myProject.order.dto.OrderStatusUpdateRequest;
import com.myProject.order.dto.PaymentStatusUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.order.dto.OrderRequest;
import com.myProject.order.dto.OrderResponse;
import com.myProject.order.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Tag(name = "Orders", description = "Order APIs")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @Operation(
        summary = "Get all orders"
    )
    public ResponseEntity<Page<OrderResponse>> getAllOrders(Pageable pageable) {
        return ResponseEntity.ok(orderService.getAllOrders(pageable));
    }
    
    @GetMapping("/{orderId}")
    @Operation(
        summary = "Get order by ID"
    )
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping("/user/{userId}")
    @Operation(
        summary = "Get order by user ID"
    )
    public ResponseEntity<List<OrderResponse>> getOrderByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrderByUserId(userId));
    }
    
    @GetMapping("/me")
    @Operation(
        summary = "Get current user's orders",
        description = "Get the orders of user who is currently authenticated"
    )
    public ResponseEntity<List<OrderResponse>> getMyOrders() {
        return ResponseEntity.ok(orderService.getMyOrders());
    }

    @PatchMapping("/{orderId}/order-status")
    @Operation(
        summary = "Update order status",
        description = "Update order status by passing order ID in path variable and order status in the request body"
    )
    public ResponseEntity<OrderResponse> updateOrderStatus(
            @PathVariable Long orderId,
            @Valid @RequestBody OrderStatusUpdateRequest request
    ) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, request));
    }

    @PatchMapping("/{orderId}/payment-status")
    @Operation(
        summary = "Update payment status",
        description = "Update payment status by passing order ID in path variable and payment status in the request body"
    )
    public ResponseEntity<OrderResponse> updatePaymentStatus(
            @PathVariable Long orderId,
            @Valid @RequestBody PaymentStatusUpdateRequest request
            ) {
        return ResponseEntity.ok(orderService.updatePaymentStatus(orderId, request));
    }

    @PostMapping("/place-order")
    @Operation(
        summary = "Place order"
    )
    public ResponseEntity<OrderResponse> placeOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.placeOrder(orderRequest));
    }

    @PostMapping("/cart/place-order")
    @Operation(
            summary = "Place order from cart"
    )
    public ResponseEntity<OrderResponse> placeOrderFromCart() {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.placeOrderFromCart());
    }

    @PatchMapping("/{orderId}/cancel")
    @Operation(
        summary = "Cancel order"
    )
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}