package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.CreditCard;
import com.example.ecommerce.service.AddressService;
import com.example.ecommerce.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class CreditCardController {
    @Autowired
    private CreditCardService creditCardService;

    @GetMapping()
    public List<CreditCard> getAllAddress(){
        return  creditCardService.getAllCard();
    }
    @PostMapping()
    public CreditCard saveAddress(@RequestBody CreditCard creditCard){
        return  creditCardService.saveCard(creditCard);
    }
    @PutMapping("{id}")
    public CreditCard updateAddress(@PathVariable Long id,@RequestBody CreditCard creditCard){
        return  creditCardService.updateCard(id,creditCard);
    }
    @DeleteMapping("{id}")
    public CreditCard deleteAddress(@PathVariable Long id){
        return  creditCardService.deleteCard(id);
    }
}
