package com.myProject.E_CommerceBackendProject.product.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewProductDto {
    
    @NotBlank(message = "Name is a required field")
    private String name;

    @NotNull(message = "Price is a required field")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private BigDecimal price;

    private String description;

    @NotNull(message = "Stock is a required field")
    @Min(value = 0, message = "Stock quantity cannot be less than zero")
    private Integer stockQuantity;
    
    @NotNull(message = "Category ID is a required field")
    private Long categoryId;

}