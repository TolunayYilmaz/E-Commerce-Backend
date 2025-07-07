package com.example.ecommerce.entity;

import java.util.List;

public class Product {

    private Long id;
    private String name;
    private String category;
    private Double price;
    private Integer stock;
    private boolean isActive;
    private  Long categoryId;
    private Long sellerId;
    private List<ProductImage> images;

}
