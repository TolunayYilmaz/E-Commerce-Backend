package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.ProductComment;
import com.example.ecommerce.entity.ProductImage;

import java.util.List;

public record ProductResponseDto(Long id,
                                 String name,
                                 String description,
                                 Double price,
                                 Integer stock,
                                 Long category_id,
                                 Double rating,
                                 Integer sell_count,
                                 Long storeId,
                                 List<ProductImage> images,
                                 List<ProductComment> comments) {
}
