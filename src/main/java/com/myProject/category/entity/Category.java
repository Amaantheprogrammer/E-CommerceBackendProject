package com.myProject.category.entity;

import java.util.ArrayList;
import java.util.List;

import com.myProject.product.entity.Product;

import jakarta.persistence.CascadeType;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
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
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE}) 
    @Builder.Default
    private List<Product> products = new ArrayList<>();
}