package com.myProject.payment.service;

import java.math.BigDecimal;

import com.myProject.payment.dto.BankAccountDto;

public interface BankAccountService {

    BankAccountDto getByUserId(Long userId);

    BankAccountDto depositFunds(Long userId, BigDecimal amount);

    BankAccountDto withdrawFunds(Long userId, BigDecimal amount);

}