package com.myProject.payment.service;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myProject.exception.BadRequestException;
import com.myProject.exception.ResourceNotFoundException;
import com.myProject.payment.dto.BankAccountResponse;
import com.myProject.payment.entity.BankAccount;
import com.myProject.payment.repository.BankAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankAccountService {
    
    private final BankAccountRepository bankAccountRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public BankAccountResponse getByUserId(Long userId) {
        BankAccount bankAccount = bankAccountRepository.findByUser_Id(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("Account not found with user ID: " + userId));
        return modelMapper.map(bankAccount, BankAccountResponse.class);
    }

    @Transactional
    public BankAccountResponse depositFunds(Long userId, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Deposit amount must be greater than zero.");
        }
        BankAccount bankAccount = bankAccountRepository.findByUser_Id(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("Account not found with user ID: " + userId));
        bankAccount.setBalance(bankAccount.getBalance().add(amount));
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        return modelMapper.map(savedBankAccount, BankAccountResponse.class);
    }

    @Transactional
    public BankAccountResponse withdrawFunds(Long userId, BigDecimal amount) {
        if (amount ==  null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Withdraw amount must be greater than 0");
        }
        BankAccount bankAccount = bankAccountRepository.findByUser_Id(userId)
                         .orElseThrow(() -> new ResourceNotFoundException("Account not found with user ID: " + userId));
        if (bankAccount.getBalance().compareTo(amount) < 0) {
            throw new BadRequestException("Insufficient balance in the bank account");
        }
        bankAccount.setBalance(bankAccount.getBalance().subtract(amount));
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        return modelMapper.map(savedBankAccount, BankAccountResponse.class);
    }
}