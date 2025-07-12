package com.example.ecommerce.service;

import com.example.ecommerce.entity.CreditCard;

import java.util.List;

public interface CreditCardService {

    CreditCard saveCard(CreditCard creditCard);
    CreditCard updateCard(Long id,CreditCard creditCard);
    CreditCard deleteCard(Long id);
    List<CreditCard> getAllCard();
}
