package com.example.ecommerce.service;

import com.example.ecommerce.dto.CreditCardRequestDto;
import com.example.ecommerce.dto.CreditCardResponse;
import com.example.ecommerce.entity.CreditCard;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exceptions.ApiException;
import com.example.ecommerce.mapper.CreditCardMapper;
import com.example.ecommerce.repository.CreditCardRepository;
import com.example.ecommerce.service.parent.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl extends BaseService implements CreditCardService{
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private CreditCardMapper creditCardMapper;

    @Override
    public CreditCardResponse saveCard(CreditCardRequestDto creditCardRequestDto) {
        User user=getCurrentUser();
        CreditCard creditCard=creditCardMapper.toEntity(creditCardRequestDto, user.getId());
        if(user.getCreditCardList().contains(creditCard)){
            throw new ApiException("Aynı adresi ekleyemezsin",HttpStatus.CONFLICT);
        }
        user.getCreditCardList().add(creditCard);
        return creditCardMapper.toResponse(creditCardRepository.save(creditCard));
    }

    @Override
    public CreditCard updateCard(CreditCardRequestDto creditCardRequestDto) {
        User user=getCurrentUser();
        CreditCard creditCardOptional = creditCardRepository.findById(creditCardRequestDto.id()).orElseThrow(()->   new ApiException("Kart bulunamadı", HttpStatus.NOT_FOUND));
        if(!user.getCreditCardList().contains(creditCardOptional)){
            throw new ApiException("Bu kartı güncelleme yetkiniz bulunmamaktadır",HttpStatus.NOT_FOUND);
        }
        creditCardOptional.setCardName(creditCardRequestDto.nameOnCard());
        creditCardOptional.setCardNumber(creditCardRequestDto.cardNo());
        creditCardOptional.setYear(creditCardRequestDto.expireYear());
        creditCardOptional.setMonth(creditCardRequestDto.expireMonth());
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
    public List<CreditCardResponse> getAllCard() {
        User user=getCurrentUser();
       if( user.getCreditCardList().isEmpty()){
           throw new ApiException("Kullanıcıya ait kart bulunmamaktadır.",HttpStatus.NOT_FOUND);
       }
        return user.getCreditCardList().stream().map(card->creditCardMapper.toResponse(card)).toList();
    }

    @Override
    public CreditCard getCard(String cardNo) {
        return creditCardRepository.getCreditCard(cardNo).orElseThrow(()->new ApiException("Kredi kartı bulunamadı",HttpStatus.NOT_FOUND));
    }


}
