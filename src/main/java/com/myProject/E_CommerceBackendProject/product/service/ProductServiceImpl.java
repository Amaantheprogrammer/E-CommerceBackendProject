package com.myProject.E_CommerceBackendProject.product.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myProject.E_CommerceBackendProject.category.entity.Category;
import com.myProject.E_CommerceBackendProject.category.repository.CategoryRepository;
import com.myProject.E_CommerceBackendProject.exception.ResourceNotFoundException;
import com.myProject.E_CommerceBackendProject.product.dto.NewProductDto;
import com.myProject.E_CommerceBackendProject.product.dto.ProductDto;
import com.myProject.E_CommerceBackendProject.product.entity.Product;
import com.myProject.E_CommerceBackendProject.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    
    @Override
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        return productRepository.findAllWithCategory(pageable).map(this::mapToDto);
    }
    
    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        return mapToDto(product);
    }
    
    @Override
    public List<ProductDto> getProductByNameContainingIgnoreCase(String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        return products.stream().map(this::mapToDto).toList();
    }
    
    @Override
    public List<ProductDto> getProductByNameContainingIgnoreCaseAndPriceLessThan(String name, BigDecimal price) {
        List<Product> products = productRepository.findByNameContainingIgnoreCaseAndPriceLessThan(name, price);
        return products.stream().map(this::mapToDto).toList();
    }
    
    @Override
    public ProductDto createNewProduct(NewProductDto newProductDto) {
        // Check if category exists by id and store it in an object
        Category category = categoryRepository.findById(newProductDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + newProductDto.getCategoryId()));
        // Convert NewProductDto to Product 
        Product product = Product.builder()
                .name(newProductDto.getName())
                .price(newProductDto.getPrice())
                .description(newProductDto.getDescription())
                .stockQuantity(newProductDto.getStockQuantity())
                .category(category)
                .build();
        // Save in database as product and return productDto
        return mapToDto(productRepository.save(product));    
    }
    
    @Override
    public List<ProductDto> getProductsByCategoryId(Long id) {
        if(!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with ID: " + id);
        }
        List<Product> products = productRepository.findProductsByCategoryId(id);
        return products.stream().map(this::mapToDto).toList();

    }

    private ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .stockQuantity(product.getStockQuantity())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .build();
    }

}
