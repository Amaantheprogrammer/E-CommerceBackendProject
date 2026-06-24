package com.myProject.E_CommerceBackendProject.payment.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountDto {
    private Long id;
    private BigDecimal balance;
    private Long userId;
    private String userName;
}