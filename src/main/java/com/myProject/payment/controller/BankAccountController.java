package com.myProject.E_CommerceBackendProject.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.E_CommerceBackendProject.payment.dto.BankAccountDto;
import com.myProject.E_CommerceBackendProject.payment.dto.UpdateBankAccountDto;
import com.myProject.E_CommerceBackendProject.payment.service.BankAccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank-accounts")
public class BankAccountController {
    
    private final BankAccountService bankAccountService;
    
    @GetMapping("/{userId}")
    public ResponseEntity<BankAccountDto> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(bankAccountService.getByUserId(userId));
    }
    
    @PostMapping("/deposit")
    public ResponseEntity<BankAccountDto> depositFunds(@RequestBody UpdateBankAccountDto updateBankAccountDto) {
        return ResponseEntity.ok(bankAccountService.depositFunds(updateBankAccountDto.getUserId(), updateBankAccountDto.getAmount()));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<BankAccountDto> withdrawFunds(@RequestBody UpdateBankAccountDto updateBankAccountDto) {
        return ResponseEntity.ok(bankAccountService.withdrawFunds(updateBankAccountDto.getUserId(), updateBankAccountDto.getAmount()));
    }

}