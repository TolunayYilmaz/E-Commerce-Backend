package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderResponseDto;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.mapper.OrderMapper;
import com.example.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream().map(order -> orderMapper.toResponse(order)).toList();
    }

    @Override
    public OrderResponseDto createOrder(Order order) {
        return orderMapper.toResponse(orderRepository.save(order));
    }
}
