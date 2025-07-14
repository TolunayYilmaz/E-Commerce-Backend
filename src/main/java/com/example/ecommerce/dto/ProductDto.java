package com.example.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    @JsonProperty("store_id")
    private Long sellerId;
    @JsonProperty("category_id")
    private Long categoryId;
    private Double rating;
    @JsonProperty("sell_count")
    private Integer sellCount;
    private List<ImageDTO> images;

    @Data
    public static class ImageDTO {
        private String url;
        private int index;
    }
}
