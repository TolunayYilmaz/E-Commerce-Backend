package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.CreditCardRequestDto;
import com.example.ecommerce.dto.CreditCardResponse;
import com.example.ecommerce.entity.CreditCard;
import org.springframework.stereotype.Component;

@Component
public class CreditCardMapper {
    public CreditCardResponse toResponse(CreditCard creditCard){
      return new CreditCardResponse(creditCard.getId(),creditCard.getUserId(),creditCard.getCardNumber(),creditCard.getMonth(),creditCard.getYear(),creditCard.getCardName());

    }
    public CreditCard toEntity(CreditCardRequestDto dto, Long userId) {
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(dto.cardNo());
        creditCard.setMonth(dto.expireMonth());
        creditCard.setYear(dto.expireYear());
        creditCard.setCardName(dto.nameOnCard());
        creditCard.setUserId(userId);
        return creditCard;
    }
}
