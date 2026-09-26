package com.myProject.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.myProject.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByNameIgnoreCase(@Param("name") String name);

}
