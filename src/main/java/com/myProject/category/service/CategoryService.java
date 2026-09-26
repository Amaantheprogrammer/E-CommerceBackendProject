package com.myProject.category.service;

import java.util.List;

import com.myProject.category.dto.CategoryDto;
import com.myProject.category.dto.NewCategoryDto;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long id);

    CategoryDto createCategory(NewCategoryDto newCategoryDto);

    CategoryDto updateCategory(Long id, NewCategoryDto newCategoryDto);

}
