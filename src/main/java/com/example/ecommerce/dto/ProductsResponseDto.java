package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class ProductsResponseDto {

    public ProductsResponseDto(List<ProductResponseDto> products) {
        this.products = products;
        this.total=products.size();
    }

    private List<ProductResponseDto> products;
   private Integer total;
}
