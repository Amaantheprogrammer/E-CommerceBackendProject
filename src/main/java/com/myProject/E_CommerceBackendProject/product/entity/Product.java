package com.myProject.E_CommerceBackendProject.product.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.myProject.E_CommerceBackendProject.category.entity.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, precision = 10, scale = 2) // Up to 10 digits and rounded to 2 decimal place
    private BigDecimal price;
    
    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Integer stockQuantity;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    // Owning side
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false) 
    @ToString.Exclude
    private Category category;

    @PrePersist // Called by hibernate
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate // Called by hibernate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}