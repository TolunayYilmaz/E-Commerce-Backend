package com.example.ecommerce.service;

import com.example.ecommerce.entity.CreditCard;
import com.example.ecommerce.exceptions.ApiException;
import com.example.ecommerce.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService{
    @Autowired
    private CreditCardRepository creditCardRepository;


    @Override
    public CreditCard saveCard(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    @Override
    public CreditCard updateCard(Long id, CreditCard creditCard) {

        Optional<CreditCard> creditCardOptional = creditCardRepository.findById(id);
        creditCardOptional.orElseThrow(()->
                new ApiException("Kart bulunamadı", HttpStatus.NOT_FOUND)
        );
        return creditCardRepository.save(creditCard);
    }

    @Override
    public CreditCard deleteCard(Long id) {
        Optional<CreditCard> creditCardOptional = creditCardRepository.findById(id);
        CreditCard creditCard= creditCardOptional.orElseThrow(()->
                new ApiException("Kart bulunamadı", HttpStatus.NOT_FOUND)
        );
        creditCardRepository.delete(creditCard);
        return creditCard;
    }

    @Override
    public List<CreditCard> getAllCard() {
        return creditCardRepository.findAll();
    }
}
