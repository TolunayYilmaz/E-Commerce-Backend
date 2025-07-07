package com.example.ecommerce.entity;

import java.util.List;

public class Order {
    private Long id;
    private Long userId;
    private List<OrderItem> orderItems;
}
