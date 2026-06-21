package com.myProject.E_CommerceBackendProject.category.service;

import java.util.List;

import com.myProject.E_CommerceBackendProject.category.dto.CategoryDto;

public interface CategoryService {
    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long id);

}
