package com.myProject.E_CommerceBackendProject.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myProject.E_CommerceBackendProject.category.dto.CategoryDto;
import com.myProject.E_CommerceBackendProject.category.entity.Category;
import com.myProject.E_CommerceBackendProject.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(this::mapToDto).toList();
    }

    private CategoryDto mapToDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
