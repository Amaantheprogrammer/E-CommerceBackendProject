package com.myProject.E_CommerceBackendProject.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewCategoryDto {
    @NotBlank
    private String name;
    private String description;
}
