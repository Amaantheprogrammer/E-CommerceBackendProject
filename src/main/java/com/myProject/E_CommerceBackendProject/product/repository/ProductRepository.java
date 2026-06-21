package com.myProject.E_CommerceBackendProject.product.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myProject.E_CommerceBackendProject.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p JOIN FETCH p.category")
    Page<Product> findAllWithCategory(Pageable pageable);
    
    @Query("SELECT p FROM Product p JOIN FETCH p.category WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> findByNameContainingIgnoreCase(@Param("name") String name);
    
    @Query("SELECT p FROM Product p JOIN FETCH p.category WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) AND p.price < :price")
    List<Product> findByNameContainingIgnoreCaseAndPriceLessThan(@Param("name")
        String name, @Param("price") BigDecimal price);
    
    @Query("SELECT p FROM Product p JOIN FETCH p.category WHERE p.category.id = :id")
    List<Product> findProductsByCategoryId(@Param("id") Long id);
    
    @Query("SELECT p FROM Product p JOIN FETCH p.category WHERE p.category.id = :id AND p.price < :price")
    List<Product> findByCategoryIdAndPriceLessThan(@Param("id") Long id, @Param("price") BigDecimal price);
    
}