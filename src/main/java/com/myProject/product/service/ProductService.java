package com.myProject.E_CommerceBackendProject.product.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.myProject.E_CommerceBackendProject.product.dto.NewProductRequest;
import com.myProject.E_CommerceBackendProject.product.dto.ProductResponse;
import com.myProject.E_CommerceBackendProject.product.dto.UpdateProductRequest;

public interface ProductService {

    Page<ProductResponse> getAllProducts(Pageable pageable);
    
    ProductResponse getProductById(Long id);

    List<ProductResponse> getProductByNameContainingIgnoreCase(String name);

    List<ProductResponse> getProductByNameContainingIgnoreCaseAndPriceLessThan(String name, BigDecimal price);

    List<ProductResponse> getProductsByCategoryIdAndPriceLessThan(Long id, BigDecimal price);

    ProductResponse createNewProduct(NewProductRequest newProductRequest);

    ProductResponse updateProduct(Long id, UpdateProductRequest updateProductRequest);

    ProductResponse updatePartialProduct(Long id, UpdateProductRequest updateProductRequest);

    void deleteProductById(Long id);

}