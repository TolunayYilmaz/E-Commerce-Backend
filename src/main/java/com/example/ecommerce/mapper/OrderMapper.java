package com.example.ecommerce.mapper;
import com.example.ecommerce.dto.OrderResponseDto;
import com.example.ecommerce.entity.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderMapper {

    public OrderResponseDto toResponse(Order order){
        OrderResponseDto orderResponseDto = new OrderResponseDto(order.getId(),order.getUser().getUsername(),order.getTotalPrice(),LocalDateTime.now(),order.getOrderItems().size());
        return  orderResponseDto;
    }
}
