package com.myProject.E_CommerceBackendProject.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myProject.E_CommerceBackendProject.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findByUserId(Long userId);

}