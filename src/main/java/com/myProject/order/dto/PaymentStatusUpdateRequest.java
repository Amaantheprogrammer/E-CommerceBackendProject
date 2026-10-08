package com.myProject.order.dto;

import com.myProject.order.entity.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStatusUpdateRequest {
    @NotBlank(message = "Payment status is required")
    private PaymentStatus paymentStatus;
}
