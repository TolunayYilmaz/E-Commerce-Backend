package com.example.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record OrderRequestDto(@JsonProperty("address_id")Long addressId,
                              @JsonProperty("order_date")LocalDateTime orderDate,
                              @JsonProperty("card_no")String cardNo,
                              @JsonProperty("card_name")String cardName,
                              @JsonProperty("card_expire_month")Integer  cardExpireMonth,
                              @JsonProperty("card_expire_year")Integer cardExpireYear,
                              @JsonProperty("card_ccv")Integer cardCcv,
                              @JsonProperty("price")Double totalPrice,
                              List<OrderRequestItemDto> products) {
}
//Request tarafı düzelticelcek