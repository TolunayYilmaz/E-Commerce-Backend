package com.example.ecommerce.service;

import com.example.ecommerce.entity.CreditCard;

import java.util.List;
import java.util.Optional;

public interface CreditCardService {

    CreditCard saveCard(CreditCard creditCard);
    CreditCard updateCard(Long id,CreditCard creditCard);
    CreditCard deleteCard(Long id);
    List<CreditCard> getAllCard();
    CreditCard getCard(Long cardNo);
}
