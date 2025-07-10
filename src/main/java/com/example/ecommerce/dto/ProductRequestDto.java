package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Category;

public record ProductRequestDto(String name,
                                Double price,
                                Integer stock,
                                Category categoryName,
                                Long sellerId) {
}
