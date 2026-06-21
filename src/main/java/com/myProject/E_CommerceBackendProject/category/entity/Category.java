package com.myProject.E_CommerceBackendProject.category.entity;

import java.util.ArrayList;
import java.util.List;

import com.myProject.E_CommerceBackendProject.product.entity.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
@Entity
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    
    // Inverse side
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY) 
    @ToString.Exclude
    @Builder.Default
    private List<Product> products = new ArrayList<>();
}