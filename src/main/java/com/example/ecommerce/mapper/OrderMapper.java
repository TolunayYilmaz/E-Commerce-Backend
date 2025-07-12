package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.OrderResponseDto;
import com.example.ecommerce.dto.ProductResponseDto;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.Product;

import java.time.LocalDateTime;

public class OrderMapper {

    public OrderResponseDto toResponse(Order order){
        OrderResponseDto orderResponseDto = new OrderResponseDto(order.getId(),order.getUser().getUsername(),order.getTotalPrice(),LocalDateTime.now(),order.getOrderItems().size());
        return  orderResponseDto;
    }
}
