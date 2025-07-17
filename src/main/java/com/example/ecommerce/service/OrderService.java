package com.example.ecommerce.service;
import com.example.ecommerce.dto.OrderRequestDto;
import com.example.ecommerce.dto.OrderResponseDto;
import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.Order;
import java.util.List;

public interface OrderService {
    List<OrderResponseDto> getAllOrders();
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
    OrderResponseDto updateOrder(Long id, Order order);
    OrderResponseDto deleteOrder(Long id);

}
