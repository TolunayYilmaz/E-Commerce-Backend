package com.example.ecommerce.dto;

import com.example.ecommerce.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDto(Long id, Long userId, Long addressId,LocalDateTime orderDate, Long cardNo, String cardName,
                               Integer  cardExpireMonth, Integer cardExpireYear, Double totalPrice,
                               List<OrderItem> orderItems) {
}
