package com.example.ecommerce.dto;

import com.example.ecommerce.entity.ProductImage;

import java.util.List;

public record OrderItemResponseDto(Long id, String name, String description, Double price, Integer count, List<ProductImage> images) {
}
