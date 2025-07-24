package com.example.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StoreRequestDto(String name, String phone, @JsonProperty("tax_no") String taxNo,@JsonProperty("bank_account") String bankAccount){
}
