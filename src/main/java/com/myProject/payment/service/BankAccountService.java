package com.myProject.E_CommerceBackendProject.payment.service;

import java.math.BigDecimal;

import com.myProject.E_CommerceBackendProject.payment.dto.BankAccountDto;

public interface BankAccountService {

    BankAccountDto getByUserId(Long userId);

    BankAccountDto depositFunds(Long userId, BigDecimal amount);

    BankAccountDto withdrawFunds(Long userId, BigDecimal amount);

}