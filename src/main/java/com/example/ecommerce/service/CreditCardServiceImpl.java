package com.example.ecommerce.service;

import com.example.ecommerce.entity.CreditCard;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exceptions.ApiException;
import com.example.ecommerce.repository.CreditCardRepository;
import com.example.ecommerce.service.parent.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl extends BaseService implements CreditCardService{
    @Autowired
    private CreditCardRepository creditCardRepository;


    @Override
    public CreditCard saveCard(CreditCard creditCard) {
        User user=getCurrentUser();
        if(user.getCreditCardList().contains(creditCard)){
            throw new ApiException("Aynı adresi ekleyemezsin",HttpStatus.CONFLICT);
        }
        user.getCreditCardList().add(creditCard);
        return creditCardRepository.save(creditCard);
    }

    @Override
    public CreditCard updateCard(Long id, CreditCard creditCard) {
        User user=getCurrentUser();
        CreditCard creditCardOptional = creditCardRepository.findById(id).orElseThrow(()->   new ApiException("Kart bulunamadı", HttpStatus.NOT_FOUND));
        if(!user.getCreditCardList().contains(creditCardOptional)){
            throw new ApiException("Bu kartı güncelleme yetkiniz bulunmamaktadır",HttpStatus.NOT_FOUND);
        }
        creditCardOptional.setCardName(creditCard.getCardName());
        creditCardOptional.setCardNumber(creditCard.getCardNumber());
        creditCardOptional.setYear(creditCard.getYear());
        creditCardOptional.setMonth(creditCard.getMonth());
        return creditCardRepository.save(creditCardOptional);
    }

    @Override
    public CreditCard deleteCard(Long id) {
        User user=getCurrentUser();

         CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(()->new ApiException("Kart bulunamadı", HttpStatus.NOT_FOUND));

        if(!user.getCreditCardList().contains(creditCard)){
            throw new ApiException("Kart kullanıcıya ait değil",HttpStatus.NOT_FOUND);
        }
        user.getCreditCardList().remove(creditCard);
        creditCardRepository.delete(creditCard);
        return creditCard;
    }

    @Override
    public List<CreditCard> getAllCard() {
        User user=getCurrentUser();
       if( user.getBasketList().isEmpty()){
           throw new ApiException("Kullanıcıya ait kart bulunmamaktadır.",HttpStatus.NOT_FOUND);
       }
        return user.getCreditCardList();
    }
}
