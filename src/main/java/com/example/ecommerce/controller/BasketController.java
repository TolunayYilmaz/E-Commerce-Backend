package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.Basket;
import com.example.ecommerce.repository.BasketRepository;
import com.example.ecommerce.service.AddressService;
import com.example.ecommerce.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {
    @Autowired
    private BasketService basketService;

    @GetMapping()
    public List<Basket> getAllBasket(){
        return basketService.getBasket();
    }
    @PostMapping
    public  Basket saveBasket(@RequestBody Basket basket){

        return basketService.saveBasket(basket);
    }

//    @PostMapping()
//    public Basket saveAddress(@RequestBody Address address,@RequestAttribute("userEmail") String userEmail){
//
//        return basketService.saveBasket(address,userEmail);
//    }
    @PutMapping("{id}")
    public Basket updateAddress(@PathVariable Long id,@RequestBody Basket basket){
        return  basketService.updateBasket(id,basket);
    }
    @DeleteMapping("{id}")
    public Basket deleteAddress(@PathVariable Long id){
        return  basketService.deleteBasket(id);
    }
}
