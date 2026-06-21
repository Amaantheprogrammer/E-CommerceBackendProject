package com.myProject.E_CommerceBackendProject.category.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myProject.E_CommerceBackendProject.category.dto.CategoryDto;
import com.myProject.E_CommerceBackendProject.category.dto.NewCategoryDto;
import com.myProject.E_CommerceBackendProject.category.entity.Category;
import com.myProject.E_CommerceBackendProject.category.repository.CategoryRepository;
import com.myProject.E_CommerceBackendProject.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@RequestMapping("categories")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(this::mapToDto).toList();
    }
    
    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));
        return mapToDto(category);
    }
    
    @Override
    public CategoryDto createCategory(NewCategoryDto newCategoryDto) {
        Category category = Category.builder().name(newCategoryDto.getName()).build();
        return mapToDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto updateCategory(Long id, NewCategoryDto newCategoryDto) {
        Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));
        category.setName(newCategoryDto.getName());
        return mapToDto(categoryRepository.save(category));
    }

    private CategoryDto mapToDto(Category category) {
        return CategoryDto.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
    }
}
