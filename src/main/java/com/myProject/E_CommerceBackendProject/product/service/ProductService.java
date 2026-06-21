package com.myProject.E_CommerceBackendProject.product.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.myProject.E_CommerceBackendProject.product.dto.NewProductDto;
import com.myProject.E_CommerceBackendProject.product.dto.ProductDto;

public interface ProductService {

    Page<ProductDto> getAllProducts(Pageable pageable);
    
    ProductDto getProductById(Long id);

    List<ProductDto> getProductByNameContainingIgnoreCase(String name);

    List<ProductDto> getProductByNameContainingIgnoreCaseAndPriceLessThan(String name, BigDecimal price);

    ProductDto createNewProduct(NewProductDto newProductDto);

    List<ProductDto> getProductsByCategoryId(Long id);
}