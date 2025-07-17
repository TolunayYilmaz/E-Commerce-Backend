package com.example.ecommerce.dto;

public record AddressRequestDto(String title,String name,String surname,
                                String phoneNumber,String city,String district,
                                String neighborhood,String addressDetail) {
}
