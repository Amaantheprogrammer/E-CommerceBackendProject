package com.myProject.rest_client.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequest {
    private String accountNumber;
    private BigDecimal amount;
}
