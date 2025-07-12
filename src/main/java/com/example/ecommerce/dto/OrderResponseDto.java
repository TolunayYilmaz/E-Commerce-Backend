package com.example.ecommerce.dto;

import java.time.LocalDateTime;

public record OrderResponseDto(Long id, String cardName, Double totalPrice, LocalDateTime date, Integer orderCount) {
}
