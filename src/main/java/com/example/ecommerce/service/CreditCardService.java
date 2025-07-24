package com.example.ecommerce.service;

import com.example.ecommerce.dto.CreditCardRequestDto;
import com.example.ecommerce.dto.CreditCardResponse;
import com.example.ecommerce.entity.CreditCard;

import java.util.List;
import java.util.Optional;

public interface CreditCardService {

    CreditCardResponse saveCard(CreditCardRequestDto creditCardRequestDto);
    CreditCard updateCard(CreditCardRequestDto creditCardRequestDto);
    CreditCard deleteCard(Long id);
    List<CreditCardResponse> getAllCard();
    CreditCard getCard(String cardNo);
}
