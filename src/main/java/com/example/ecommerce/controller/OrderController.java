package com.example.ecommerce.controller;

import com.example.ecommerce.dto.OrderRequestDto;
import com.example.ecommerce.dto.OrderResponseDto;
import com.example.ecommerce.dto.ProductRequestDto;
import com.example.ecommerce.dto.ProductResponseDto;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping()
    public List<OrderResponseDto> getAllProducts(){
        return orderService.getAllOrders();
    }
    @PostMapping()
    public OrderResponseDto createProduct(@RequestBody @Validated OrderRequestDto orderRequestDto){
        return orderService.createOrder(orderRequestDto);
    }
}
