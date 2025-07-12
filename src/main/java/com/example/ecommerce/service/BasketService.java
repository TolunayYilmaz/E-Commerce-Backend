package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductResponseDto;
import com.example.ecommerce.entity.Basket;

import java.util.List;

public interface BasketService {

    Basket saveBasket(Basket basket);
    Basket updateBasket(Long id, Basket basket);
    Basket deleteBasket(Long id);
    List<Basket> getBasket();
}
