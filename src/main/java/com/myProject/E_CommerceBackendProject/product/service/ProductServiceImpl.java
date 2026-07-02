package com.myProject.E_CommerceBackendProject.product.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myProject.E_CommerceBackendProject.category.entity.Category;
import com.myProject.E_CommerceBackendProject.category.repository.CategoryRepository;
import com.myProject.E_CommerceBackendProject.exception.ResourceNotFoundException;
import com.myProject.E_CommerceBackendProject.product.dto.NewProductDto;
import com.myProject.E_CommerceBackendProject.product.dto.ProductDto;
import com.myProject.E_CommerceBackendProject.product.dto.UpdateProductDto;
import com.myProject.E_CommerceBackendProject.product.entity.Product;
import com.myProject.E_CommerceBackendProject.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        log.info(">>> Fetching all products from the database");
        simulateSlowDbCall();
        return productRepository.findAllWithCategory(pageable).map(this::mapToDto);
    }
    
    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "products", key = "#id")
    public ProductDto getProductById(Long id) {
        log.info(">>> Fetching product with ID: " + id);
        simulateSlowDbCall();
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        return mapToDto(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getProductByNameContainingIgnoreCase(String name) {
        log.info(">>> Fetching product with name: " + name);
        simulateSlowDbCall();
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        return products.stream().map(this::mapToDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getProductByNameContainingIgnoreCaseAndPriceLessThan(String name, BigDecimal price) {
        log.info(">>> Fetching product with name: " + name + " and price: " + price);
        simulateSlowDbCall();
        List<Product> products = productRepository.findByNameContainingIgnoreCaseAndPriceLessThan(name, price);
        return products.stream().map(this::mapToDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getProductsByCategoryIdAndPriceLessThan(Long id, BigDecimal price) {
        log.info(">>> Fetching product with id: " + id + " and price: " + price);
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with ID: " + id);
        }
        List<Product> products = (price != null)
                ? productRepository.findByCategoryIdAndPriceLessThan(id, price)
                : productRepository.findProductsByCategoryId(id);
        return products.stream().map(this::mapToDto).toList();
    }

    @Override
    @Transactional
    public ProductDto createNewProduct(NewProductDto newProductDto) {
        log.info("Creating product with name: " + newProductDto.getName());
        simulateSlowDbCall();
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
    @Transactional
    public ProductDto updateProduct(Long id, UpdateProductDto updateProductDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        product.setName(updateProductDto.getName());
        product.setPrice(updateProductDto.getPrice());
        product.setDescription(updateProductDto.getDescription());
        product.setStockQuantity(updateProductDto.getStockQuantity());
        return mapToDto(productRepository.save(product));
    }
    
    @Override
    @Transactional
    public ProductDto updatePartialProduct(Long id, UpdateProductDto updateProductDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        if (updateProductDto.getName() != null) product.setName(updateProductDto.getName());
        if (updateProductDto.getPrice() != null) product.setPrice(updateProductDto.getPrice());
        if (updateProductDto.getDescription() != null) product.setDescription(updateProductDto.getDescription());
        if (updateProductDto.getStockQuantity() != null) product.setStockQuantity(updateProductDto.getStockQuantity());
        return mapToDto(productRepository.save(product));
    }
    
    @Override
    @Transactional
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }
    
    // Private methods
    // Product dto mapping
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
    // Slow db call for real time experience
    private void simulateSlowDbCall() {
        try {
            java.lang.Thread.sleep(500);
        } catch (InterruptedException e) {
            java.lang.Thread.currentThread().interrupt();
        }
    }
}
