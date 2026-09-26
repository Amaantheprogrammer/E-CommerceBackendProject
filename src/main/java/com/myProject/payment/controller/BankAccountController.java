package com.myProject.payment.controller;

import com.myProject.payment.service.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.payment.dto.BankAccountResponse;
import com.myProject.payment.dto.UpdateBankAccountRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank-accounts")
public class BankAccountController {
    
    private final BankAccountService bankAccountService;
    
    @GetMapping("/{userId}")
    public ResponseEntity<BankAccountResponse> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(bankAccountService.getByUserId(userId));
    }
    
    @PostMapping("/deposit")
    public ResponseEntity<BankAccountResponse> depositFunds(@RequestBody UpdateBankAccountRequest updateBankAccountRequest) {
        return ResponseEntity.ok(bankAccountService.depositFunds(updateBankAccountRequest.getUserId(), updateBankAccountRequest.getAmount()));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<BankAccountResponse> withdrawFunds(@RequestBody UpdateBankAccountRequest updateBankAccountRequest) {
        return ResponseEntity.ok(bankAccountService.withdrawFunds(updateBankAccountRequest.getUserId(), updateBankAccountRequest.getAmount()));
    }

}