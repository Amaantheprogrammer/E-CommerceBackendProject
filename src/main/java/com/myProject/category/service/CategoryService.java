package com.myProject.category.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myProject.category.dto.CategoryDto;
import com.myProject.category.dto.NewCategoryDto;
import com.myProject.category.entity.Category;
import com.myProject.category.repository.CategoryRepository;
import com.myProject.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;
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
    @Transactional
    public CategoryDto createCategory(NewCategoryDto newCategoryDto) {
        Category category = Category.builder().name(newCategoryDto.getName()).build();
        return mapToDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
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
