package com.myProject.product.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.myProject.user.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myProject.category.entity.Category;
import com.myProject.category.repository.CategoryRepository;
import com.myProject.exception.ResourceNotFoundException;
import com.myProject.product.dto.NewProductRequest;
import com.myProject.product.dto.ProductResponse;
import com.myProject.product.dto.UpdateProductRequest;
import com.myProject.product.entity.Product;
import com.myProject.product.repository.ProductRepository;
import com.myProject.user.entity.User;
import com.myProject.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'SELLER')")
    @Transactional(readOnly = true)
    @Cacheable("products")
    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        log.info(">>> Fetching all products from the database");
        simulateSlowDbCall();
        return productRepository.findAllWithCategory(pageable)
                .map(product -> modelMapper.map(product, ProductResponse.class));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'SELLER')")
    @Transactional(readOnly = true)
    @Cacheable(value = "products", key = "#id")
    public ProductResponse getProductById(Long id) {
        log.info(">>> Fetching product with ID: " + id);
        simulateSlowDbCall();
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        return modelMapper.map(product, ProductResponse.class);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getProductByNameContainingIgnoreCase(String name) {
        log.info(">>> Fetching product with name: " + name);
        simulateSlowDbCall();
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'SELLER')")
    @Transactional(readOnly = true)
    public List<ProductResponse> getProductByNameContainingIgnoreCaseAndPriceLessThan(String name, BigDecimal price) {
        log.info(">>> Fetching product with name: " + name + " and price: " + price);
        simulateSlowDbCall();
        List<Product> products = productRepository.findByNameContainingIgnoreCaseAndPriceLessThan(name, price);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getProductsByCategoryIdAndPriceLessThan(Long id, BigDecimal price) {
        log.info(">>> Fetching product with id: " + id + " and price: " + price);
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with ID: " + id);
        }
        List<Product> products = (price != null)
                ? productRepository.findByCategoryIdAndPriceLessThan(id, price)
                : productRepository.findProductsByCategoryId(id);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('SELLER')")
    @Transactional
    public ProductResponse createNewProduct(NewProductRequest newProductRequest) {
        log.info("Creating product with name: " + newProductRequest.getName());
        simulateSlowDbCall();
        // Check if category exists by id and store it in an object
        Category category = categoryRepository.findById(newProductRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + newProductRequest.getCategoryId()));
        // Convert NewProductDto to Product 
        Product product = modelMapper.map(newProductRequest, Product.class);
        product.setUser(getCurrentUser());
        // Save in database as product and return productDto
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductResponse.class);
    }

    @PreAuthorize("hasRole('SELLER')")
    @Transactional
    public ProductResponse updateProduct(Long id, UpdateProductRequest updateProductRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        product.setName(updateProductRequest.getName());
        product.setPrice(updateProductRequest.getPrice());
        product.setDescription(updateProductRequest.getDescription());
        product.setStockQuantity(updateProductRequest.getStockQuantity());
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductResponse.class);
    }

    @PreAuthorize("hasRole('SELLER')")
    @Transactional
    public ProductResponse updatePartialProduct(Long id, UpdateProductRequest updateProductRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        if (updateProductRequest.getName() != null) product.setName(updateProductRequest.getName());
        if (updateProductRequest.getPrice() != null) product.setPrice(updateProductRequest.getPrice());
        if (updateProductRequest.getDescription() != null) product.setDescription(updateProductRequest.getDescription());
        if (updateProductRequest.getStockQuantity() != null) product.setStockQuantity(updateProductRequest.getStockQuantity());
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductResponse.class);
    }

    @PreAuthorize("hasRole('SELLER')")
    @Transactional
    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        productRepository.deleteById(id);
    }

    // Slow db call for real time experience
    private void simulateSlowDbCall() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    // Get Current User
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                            .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }
}
