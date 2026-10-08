package com.myProject.order.dto;

import com.myProject.order.entity.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusUpdateRequest {
    @NotBlank(message = "Order status is required")
    private OrderStatus orderStatus;
}
