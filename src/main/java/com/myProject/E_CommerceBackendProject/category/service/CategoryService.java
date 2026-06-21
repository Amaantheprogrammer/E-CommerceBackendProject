package com.myProject.E_CommerceBackendProject.category.service;

import java.util.List;

import com.myProject.E_CommerceBackendProject.category.dto.CategoryDto;
import com.myProject.E_CommerceBackendProject.category.dto.NewCategoryDto;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long id);

    CategoryDto createCategory(NewCategoryDto newCategoryDto);

    CategoryDto updateCategory(Long id, NewCategoryDto newCategoryDto);

}
