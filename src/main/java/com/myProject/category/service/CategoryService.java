package com.myProject.category.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myProject.category.dto.CategoryResponse;
import com.myProject.category.dto.NewCategoryRequest;
import com.myProject.category.entity.Category;
import com.myProject.category.repository.CategoryRepository;
import com.myProject.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@RequestMapping("categories")
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'SELLER')")
    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                            .stream()
                            .map(category -> modelMapper.map(category, CategoryResponse.class))
                            .collect(Collectors.toList());
    }
    
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'SELLER')")
    @Transactional(readOnly = true)
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));
        return modelMapper.map(category, CategoryResponse.class);
    }
    
    @PreAuthorize("hasAnyRole('ADMIN','SELLER')")
    @Transactional
    public CategoryResponse createCategory(NewCategoryRequest newCategoryRequest) {
        Category category = Category.builder().name(newCategoryRequest.getName()).build();
        return modelMapper.map(categoryRepository.save(category), CategoryResponse.class);
    }
    
    @PreAuthorize("hasAnyRole('ADMIN','SELLER')")
    @Transactional
    public CategoryResponse updateCategory(Long id, NewCategoryRequest newCategoryRequest) {
        Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));
        category.setName(newCategoryRequest.getName());
        return modelMapper.map(categoryRepository.save(category), CategoryResponse.class);
    }
}
