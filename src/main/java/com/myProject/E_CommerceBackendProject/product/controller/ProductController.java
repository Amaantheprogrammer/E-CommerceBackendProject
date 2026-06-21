package com.myProject.E_CommerceBackendProject.product.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.E_CommerceBackendProject.product.dto.NewProductDto;
import com.myProject.E_CommerceBackendProject.product.dto.ProductDto;
import com.myProject.E_CommerceBackendProject.product.service.ProductService;

import lombok.RequiredArgsConstructor;


@RestController // REST API usage
@RequiredArgsConstructor // Objects with "final" keyword get added to the constructor
@RequestMapping("products") // Adds "products" to every http link
public class ProductController {
    
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDto>> getAllProducts(Pageable pageable) {
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProducts(@RequestParam String name, @RequestParam(required = false) BigDecimal price) {
        /*
        GET /products/search?name=phone              → name search only
        GET /products/search?name=phone&price=500 → name + price filter
        */
        if (price != null) {
            return ResponseEntity.ok(productService.getProductByNameContainingIgnoreCaseAndPriceLessThan(name, price));
        }
        return ResponseEntity.ok(productService.getProductByNameContainingIgnoreCase(name));
    }
    
    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryId(@RequestBody Long id) {
        return ResponseEntity.ok(productService.getProductsByCategoryId(id));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createNewProduct(@RequestBody NewProductDto newProductDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createNewProduct(newProductDto));
    }
}