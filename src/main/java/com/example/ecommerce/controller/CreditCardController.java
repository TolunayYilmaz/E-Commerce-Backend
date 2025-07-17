package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.CreditCard;
import com.example.ecommerce.exceptions.ApiException;
import com.example.ecommerce.service.AddressService;
import com.example.ecommerce.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creditCard")
@RequiredArgsConstructor
public class CreditCardController {
    @Autowired
    private CreditCardService creditCardService;

    @GetMapping()
    public List<CreditCard> getAllCreditCards(){
        return  creditCardService.getAllCard();
    }
    @PostMapping()
    public CreditCard saveCreditCard(@RequestBody CreditCard creditCard){
        try {
           return creditCardService.saveCard(creditCard);
        } catch (Exception e) {
            throw new ApiException("Veri tabanında aynı kart bulunmaktadır", HttpStatus.CONFLICT);
        }

    }
    @PutMapping("{id}")
    public CreditCard updateCreditCard(@PathVariable Long id,@RequestBody CreditCard creditCard){
        return  creditCardService.updateCard(id,creditCard);
    }
    @DeleteMapping("{id}")
    public CreditCard deleteCreditCard(@PathVariable Long id){
        return  creditCardService.deleteCard(id);
    }
}
