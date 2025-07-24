package com.example.ecommerce.dto;

public record RequestCreditCardDto(String card_no,Integer expire_month,Integer expire_year,String name_on_card) {
}
