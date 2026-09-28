package com.myProject.order.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myProject.exception.BadRequestException;
import com.myProject.exception.ResourceNotFoundException;
import com.myProject.order.dto.OrderRequest;
import com.myProject.order.dto.OrderResponse;
import com.myProject.order.entity.Order;
import com.myProject.order.entity.OrderItem;
import com.myProject.order.entity.OrderStatus;
import com.myProject.order.entity.PaymentMethod;
import com.myProject.order.entity.PaymentStatus;
import com.myProject.order.repository.OrderRepository;
import com.myProject.payment.entity.BankAccount;
import com.myProject.payment.repository.BankAccountRepository;
import com.myProject.product.entity.Product;
import com.myProject.product.repository.ProductRepository;
import com.myProject.user.entity.User;
import com.myProject.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ModelMapper modelMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(readOnly = true)
    public Page<OrderResponse> getAllOrders(Pageable pageable) {
        Page<Order> orders = orderRepository.findAllOrders(pageable);
        return orders.map(order -> modelMapper.map(order, OrderResponse.class));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findOrderById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        return modelMapper.map(order, OrderResponse.class);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrderByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderResponse.class))
                .collect(Collectors.toList());
    }
    
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'SELLER')")
    @Transactional(readOnly = true)
    public List<OrderResponse> getMyOrders() {
        User user = getCurrentUser();
        List<Order> orders = user.getOrders();
        return orders.stream()
                    .map(order -> modelMapper.map(order, OrderResponse.class))
                    .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('SELLER')")
    @Transactional
    public OrderResponse updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        validateOrderAuthority(order);
        if (orderStatus == OrderStatus.SHIPPED && order.getPaymentStatus() == PaymentStatus.FAILED) {
            throw new BadRequestException("Cannot ship an order with payment status " + order.getPaymentStatus() 
            + " and order status " + orderStatus);
        }
        order.setOrderStatus(orderStatus);
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderResponse.class);
    }

    @PreAuthorize("hasRole('SELLER')")
    @Transactional
    public OrderResponse updatePaymentStatus(Long orderId, PaymentStatus paymentStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        validateOrderAuthority(order);
        if (paymentStatus == PaymentStatus.PAID && order.getOrderStatus() == OrderStatus.PENDING) {
            order.setOrderStatus(OrderStatus.PLACED);
        }
        order.setPaymentStatus(paymentStatus);
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderResponse.class);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'SELLER')")
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {
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
            throw new BadRequestException("Insufficient stock for product: " + product.getName());
        }
        BigDecimal totalAmount = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        if (paymentMethod == PaymentMethod.BANK_TRANSFER) {
            BankAccount bankAccount = bankAccountRepository.findByUser_Id(user.getId())
                            .orElseThrow(()-> new ResourceNotFoundException("Bank account not found for user"));
            if (bankAccount.getBalance().compareTo(totalAmount) < 0) {
                throw new BadRequestException("Insufficient balance in the account");
            }

            bankAccount.setBalance(bankAccount.getBalance().subtract(totalAmount));

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
                .orderItems(new ArrayList<>())
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

        return modelMapper.map(orderRepository.save(order), OrderResponse.class);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'SELLER')")
    @Transactional
    public void cancelOrder(Long orderId) {
        User currentUser = getCurrentUser();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new ResourceNotFoundException("Order not found with ID: " + orderId));

        if (!order.getUser().getId().equals(currentUser.getId())) {
            throw new AuthorizationDeniedException("You can only cancel your own orders");
        }
        if (order.getOrderStatus() == OrderStatus.DELIVERED
                || order.getOrderStatus() == OrderStatus.CANCELLED) {
            throw new BadRequestException("Cannot cancel an order with order status: " + order.getOrderStatus());
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Authenticated user not found"));
    }

    private void validateOrderAuthority(Order order) {
        Long currentUserId = getCurrentUser().getId();
        order.getOrderItems().forEach(item -> {
            if (!item.getProduct().getUser().getId().equals(currentUserId)) {
                throw new AuthorizationDeniedException("You cannot update orders containing products owned by another seller");
            }
        });
    }
}
