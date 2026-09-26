package com.myProject.E_CommerceBackendProject.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.myProject.E_CommerceBackendProject.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByNameIgnoreCase(@Param("name") String name);

}
