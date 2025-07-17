package com.example.ecommerce.dto;

import com.example.ecommerce.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

public record OrderRequestDto(Long addressId, LocalDateTime orderDate, Long cardNo, String cardName,
                              Integer  cardExpireMonth, Integer cardExpireYear,Integer cardCcv, Double totalPrice,
                              List<OrderItemDto> products) {
}
//Request tarafı düzelticelcek