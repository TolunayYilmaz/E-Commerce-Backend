package com.example.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreditCardRequestDto(
        Long id,@JsonProperty("card_no")String cardNo, @JsonProperty("expire_month")Integer expireMonth, @JsonProperty("expire_year")Integer expireYear,
                                   @JsonProperty("name_on_card")String nameOnCard) {
}
