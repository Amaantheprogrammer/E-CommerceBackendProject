package com.myProject.E_CommerceBackendProject.payment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myProject.E_CommerceBackendProject.payment.entity.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    
   Optional<BankAccount> findByUser_Id(Long userId);

}