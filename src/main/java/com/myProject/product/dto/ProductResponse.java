package com.myProject.product.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.myProject.product.entity.ProductImage;
import lombok.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse implements Serializable {
    private Long id;
    private String name;
    private List<ProductImage> productImages;
    private BigDecimal price;
    private String description;
    private Integer stockQuantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long categoryId;
    private String categoryName;
}