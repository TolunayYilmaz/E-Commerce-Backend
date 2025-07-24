package com.example.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderRequestItemDto(@JsonProperty("product_id") Long productId, Integer count, String detail) {
}
