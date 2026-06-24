package com.myProject.E_CommerceBackendProject.payment.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myProject.E_CommerceBackendProject.exception.BadRequestException;
import com.myProject.E_CommerceBackendProject.exception.ResourceNotFoundException;
import com.myProject.E_CommerceBackendProject.payment.dto.BankAccountDto;
import com.myProject.E_CommerceBackendProject.payment.entity.BankAccount;
import com.myProject.E_CommerceBackendProject.payment.repository.BankAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {
    
    private final BankAccountRepository bankAccountRepository;

    @Override
    @Transactional(readOnly = true)
    public BankAccountDto getByUserId(Long userId) {
        BankAccount bankAccount = bankAccountRepository.findByUserId(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("Account not found with user ID: " + userId));
        return mapToDto(bankAccount);
    }
    
    @Override
    @Transactional
    public BankAccountDto depositFunds(Long userId, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Deposit amount must be greater than zero.");
        }
        BankAccount bankAccount = bankAccountRepository.findByUserId(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("Account not found with user ID: " + userId));
        bankAccount.setBalance(bankAccount.getBalance().add(amount));
        return mapToDto(bankAccount);
    }

    @Override
    @Transactional
    public BankAccountDto withdrawFunds(Long userId, BigDecimal amount) {
        if (amount ==  null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Withdraw amount must be greater than 0");
        }
        BankAccount bankAccount = bankAccountRepository.findByUserId(userId)
                         .orElseThrow(() -> new ResourceNotFoundException("Account not found with user ID: " + userId));
        if (bankAccount.getBalance().compareTo(amount) < 0) {
            throw new BadRequestException("Insufficient balance in the bank account");
        }
        bankAccount.setBalance(bankAccount.getBalance().subtract(amount));
        return mapToDto(bankAccount);
    }

    // Map to DTO
    private BankAccountDto mapToDto(BankAccount bankAccount) {
        return BankAccountDto.builder()
                        .id(bankAccount.getId())
                        .balance(bankAccount.getBalance())
                        .userId(bankAccount.getUser().getId())
                        .userName(bankAccount.getUser().getName())
                        .build();
    }
}