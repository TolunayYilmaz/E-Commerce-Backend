package com.example.ecommerce.dto;

import com.example.ecommerce.entity.OrderItem;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDto(Long id, @JsonProperty("user_id") Long userId,
                               @JsonProperty("address_id")Long addressId,
                               @JsonProperty("order_date")LocalDateTime orderDate,
                               @JsonProperty("card_no")String cardNo,
                               @JsonProperty("card_name")String cardName,
                               @JsonProperty("card_expire_month")Integer  cardExpireMonth,
                               @JsonProperty("card_expire_year")Integer cardExpireYear,
                               @JsonProperty("price")Double totalPrice,
                               List<OrderItemResponseDto> products) {
}
