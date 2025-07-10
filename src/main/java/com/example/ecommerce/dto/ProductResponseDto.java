package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.ProductComment;
import com.example.ecommerce.entity.ProductImage;

import java.util.List;

public record ProductResponseDto(Long id,
                                 String name,
                                 Double price,
                                 Integer stock,
                                 Category categoryName,
                                 Long sellerId,
                                 List<ProductImage> images,
                                 List<ProductComment> comments) {
}
