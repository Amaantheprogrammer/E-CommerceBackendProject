package com.myProject.E_CommerceBackendProject.order.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myProject.E_CommerceBackendProject.exception.BadRequestException;
import com.myProject.E_CommerceBackendProject.exception.ResourceNotFoundException;
import com.myProject.E_CommerceBackendProject.order.dto.OrderDto;
import com.myProject.E_CommerceBackendProject.order.dto.OrderItemDto;
import com.myProject.E_CommerceBackendProject.order.dto.OrderRequest;
import com.myProject.E_CommerceBackendProject.order.entity.Order;
import com.myProject.E_CommerceBackendProject.order.entity.OrderItem;
import com.myProject.E_CommerceBackendProject.order.entity.OrderStatus;
import com.myProject.E_CommerceBackendProject.order.entity.PaymentMethod;
import com.myProject.E_CommerceBackendProject.order.entity.PaymentStatus;
import com.myProject.E_CommerceBackendProject.order.repository.OrderRepository;
import com.myProject.E_CommerceBackendProject.payment.entity.BankAccount;
import com.myProject.E_CommerceBackendProject.payment.repository.BankAccountRepository;
import com.myProject.E_CommerceBackendProject.product.entity.Product;
import com.myProject.E_CommerceBackendProject.product.repository.ProductRepository;
import com.myProject.E_CommerceBackendProject.user.entity.User;
import com.myProject.E_CommerceBackendProject.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final BankAccountRepository bankAccountRepository;

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

    @Override
    @Transactional
    public OrderDto updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        if (orderStatus == OrderStatus.SHIPPED && order.getPaymentStatus() == PaymentStatus.FAILED) {
            throw new IllegalStateException("Cannot ship an order with failed payment status");
        }
        order.setOrderStatus(orderStatus);
        return mapToDto(orderRepository.save(order));
    }

    @Override
    @Transactional
    public OrderDto updatePaymentStatus(Long orderId, PaymentStatus paymentStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        if (paymentStatus == PaymentStatus.PAID && order.getOrderStatus() == OrderStatus.PENDING) {
            order.setOrderStatus(OrderStatus.PLACED);
        }
        order.setPaymentStatus(paymentStatus);
        return mapToDto(orderRepository.save(order));
    }

    @Override
    @Transactional
    public OrderDto placeOrder(OrderRequest orderRequest) {
        User user = getCurrentUser();
        Long productId = orderRequest.getProductId();
        Product product = productRepository.findById(productId)
                .orElseThrow(()
                        -> new ResourceNotFoundException(
                        "Product not found with ID: " + productId));
        Integer quantity = orderRequest.getQuantity();
        PaymentMethod paymentMethod = orderRequest.getPaymentMethod();
        Integer stockQuantity = product.getStockQuantity();
        if (stockQuantity < quantity) {
            throw new BadRequestException(
                    "Insufficient stock for product: "
                    + product.getName());
        }
        BigDecimal totalAmount= product.getPrice().multiply(BigDecimal.valueOf(quantity));
        if (paymentMethod == PaymentMethod.BANK_TRANSFER) {
            BankAccount bankAccount = bankAccountRepository.findByUser_Id(user.getId())
                            .orElseThrow(()-> new ResourceNotFoundException("Bank account not found for user"));
            if (bankAccount.getBalance().compareTo(totalAmount) < 0) {
                throw new BadRequestException(
                        "Insufficient balance in the account");
            }

            bankAccount.setBalance(
                    bankAccount.getBalance().subtract(totalAmount));

            bankAccountRepository.save(bankAccount);
        }
        Order order = Order.builder()
                .user(user)
                .totalAmount(totalAmount)
                .paymentMethod(paymentMethod)
                .orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.PENDING)
                .paymentStatus(
                        paymentMethod == PaymentMethod.BANK_TRANSFER
                                ? PaymentStatus.PAID
                                : PaymentStatus.PENDING)
                .build();
        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(quantity)
                .priceAtPurchase(product.getPrice())
                .build();

        order.getOrderItems().add(orderItem);

        product.setStockQuantity(stockQuantity - quantity);

        productRepository.save(product);

        return mapToDto(orderRepository.save(order));
    }

    @Override
    @Transactional
    public void cancelOrder(Long orderId) {
        User currentUser = getCurrentUser();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()
                        -> new ResourceNotFoundException(
                        "Order not found with ID: " + orderId));

        if (!order.getUser().getId().equals(currentUser.getId())) {
            throw new BadRequestException(
                    "You can only cancel your own orders");
        }
        if (order.getOrderStatus() == OrderStatus.DELIVERED
                || order.getOrderStatus() == OrderStatus.CANCELLED) {
            throw new BadRequestException(
                    "Cannot cancel an order with order status: "
                    + order.getOrderStatus());
        }
        order.getOrderItems().forEach(item -> {
            Product product = item.getProduct();
            product.setStockQuantity(product.getStockQuantity() + item.getQuantity());
            productRepository.save(product);
        });
        if (order.getPaymentStatus() == PaymentStatus.PAID && order.getPaymentMethod() == PaymentMethod.BANK_TRANSFER) {
            BankAccount bankAccount = bankAccountRepository.findByUser_Id(currentUser.getId())
                            .orElseThrow(()-> new ResourceNotFoundException("Bank account not found"));

            bankAccount.setBalance(bankAccount.getBalance().add(order.getTotalAmount()));

            bankAccountRepository.save(bankAccount);
        }

        order.setOrderStatus(OrderStatus.CANCELLED);

        order.setPaymentStatus(PaymentStatus.REFUNDED);

        orderRepository.save(order);
    }

    private User getCurrentUser() {

        Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(()
                        -> new ResourceNotFoundException(
                        "Authenticated user not found"));
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
                .orderStatus(order.getOrderStatus())
                .paymentStatus(order.getPaymentStatus())
                .build();
    }
}
