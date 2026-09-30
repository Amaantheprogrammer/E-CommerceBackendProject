package com.myProject.product.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.myProject.category.entity.Category;
import com.myProject.user.entity.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
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

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> productImages = new ArrayList<>();
    
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Owning side
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false) 
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