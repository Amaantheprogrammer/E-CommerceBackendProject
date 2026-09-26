package com.myProject.product.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.product.dto.NewProductRequest;
import com.myProject.product.dto.ProductResponse;
import com.myProject.product.dto.UpdateProductRequest;
import com.myProject.product.service.ProductService;

import lombok.RequiredArgsConstructor;


@RestController // REST API usage
@RequiredArgsConstructor // Objects with "final" keyword get added to the constructor
@RequestMapping("/products") // Adds "products" to every http link
public class ProductController {
    
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(Pageable pageable) {
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam String name, @RequestParam(required = false) BigDecimal price) {
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
    public ResponseEntity<List<ProductResponse>> getProductsByCategoryId(@PathVariable Long id, @RequestParam(required = false) BigDecimal price) {
        return ResponseEntity.ok(productService.getProductsByCategoryIdAndPriceLessThan(id, price));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createNewProduct(@RequestBody NewProductRequest newProductRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createNewProduct(newProductRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest updateProductRequest) {
        return ResponseEntity.ok(productService.updateProduct(id, updateProductRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> updatePartialProduct(@PathVariable Long id, @RequestBody UpdateProductRequest updateProductRequest) {
        return ResponseEntity.ok(productService.updatePartialProduct(id, updateProductRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}