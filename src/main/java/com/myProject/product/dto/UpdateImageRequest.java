package com.myProject.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateImageRequest {
    @NotNull(message = "Image ID is a required field")
    private Long imageId;
    @NotBlank(message = "Image URL is a required field")
    private String imageUrl;
}
