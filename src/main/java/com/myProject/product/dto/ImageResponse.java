package com.myProject.product.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse implements Serializable {
    private Long id;
    private String imageUrl;
}
