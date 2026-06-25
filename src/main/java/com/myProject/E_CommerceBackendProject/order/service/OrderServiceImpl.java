package com.myProject.E_CommerceBackendProject.order.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myProject.E_CommerceBackendProject.exception.ResourceNotFoundException;
import com.myProject.E_CommerceBackendProject.order.dto.OrderDto;
import com.myProject.E_CommerceBackendProject.order.dto.OrderItemDto;
import com.myProject.E_CommerceBackendProject.order.entity.Order;
import com.myProject.E_CommerceBackendProject.order.entity.OrderItem;
import com.myProject.E_CommerceBackendProject.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = orders.stream()
                    .map(this::mapToDto)
                    .toList();
        return orderDtos;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDto getByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        return mapToDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        List<OrderDto> orderDtos = orders.stream()
                    .map(this::mapToDto)
                    .toList();
        return orderDtos;
    }

    

    // Map to DTO
    private OrderItemDto orderItemDtoConversion(OrderItem orderItem) {
        return OrderItemDto.builder()
                    .id(orderItem.getId())
                    .orderId(orderItem.getOrder().getId())
                    .productId(orderItem.getProduct().getId())
                    .quantity(orderItem.getQuantity())
                    .priceAtPurchase(orderItem.getPriceAtPurchase())
                    .build();
    }
    private OrderDto mapToDto(Order order) {
        List<OrderItemDto> orderItemDtos = order.getOrderItems()
                    .stream()
                    .map(this::orderItemDtoConversion)
                    .toList();
        BigDecimal totalAmount = order.getOrderItems()
                    .stream()
                    .map(item -> item.getPriceAtPurchase().multiply(BigDecimal.valueOf(item.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        return OrderDto.builder()
                    .id(order.getId())
                    .userId(order.getUser().getId())
                    .userName(order.getUser().getName())
                    .orderItems(orderItemDtos)
                    .totalAmount(totalAmount)
                    .paymentMethod(order.getPaymentMethod())
                    .orderDate(order.getOrderDate())
                    .build();
    }
}