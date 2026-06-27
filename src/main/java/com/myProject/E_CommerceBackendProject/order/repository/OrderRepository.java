package com.myProject.E_CommerceBackendProject.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myProject.E_CommerceBackendProject.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o JOIN FETCH o.user JOIN FETCH o.orderItems WHERE o.user.id = :userId")
    List<Order> findByUserId(@Param("userId") Long userId);

}
