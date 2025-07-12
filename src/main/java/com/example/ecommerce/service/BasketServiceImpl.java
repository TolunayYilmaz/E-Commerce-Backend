package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductResponseDto;
import com.example.ecommerce.entity.Basket;
import com.example.ecommerce.entity.CreditCard;
import com.example.ecommerce.exceptions.ApiException;
import com.example.ecommerce.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    @Autowired
    private BasketRepository basketRepository;
    @Override
    public Basket saveBasket(Basket basket) {
        return basketRepository.save(basket);
    }

    @Override
    public Basket updateBasket(Long id ,Basket basket) {
        Optional<Basket> basketOptional = basketRepository.findById(id);
        basketOptional.orElseThrow(()->
                new ApiException("Kart bulunamadı", HttpStatus.NOT_FOUND)
        );
        return basketRepository.save(basket);
    }

    @Override
    public Basket deleteBasket(Long id) {
        Optional<Basket> basketOptional = basketRepository.findById(id);
       Basket basket= basketOptional.orElseThrow(()->
                new ApiException("Kart bulunamadı", HttpStatus.NOT_FOUND)
        );
       basketRepository.delete(basket);
        return basket;
    }

    @Override
    public List<Basket> getBasket() {
    return  basketRepository.findAll();
    }
}
