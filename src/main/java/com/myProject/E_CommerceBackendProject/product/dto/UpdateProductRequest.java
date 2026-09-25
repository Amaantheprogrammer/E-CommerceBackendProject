package com.myProject.E_CommerceBackendProject.product.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductDto {
    private String name;
    private BigDecimal price;
    private String description;
    private Integer stockQuantity;
}