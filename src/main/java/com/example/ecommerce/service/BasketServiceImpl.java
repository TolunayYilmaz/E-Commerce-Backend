package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductResponseDto;
import com.example.ecommerce.entity.Basket;
import com.example.ecommerce.entity.CreditCard;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exceptions.ApiException;
import com.example.ecommerce.repository.BasketRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.parent.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl extends BaseService implements BasketService {
    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;
    @Override
    public Basket saveBasket(Basket basket) {

        User user=getCurrentUser();
        user.getBasketList().add(basket);
        basket.setUserId(getCurrentUser().getId());
        return basketRepository.save(basket);
    }

    @Override
    public Basket updateBasket(Long id ,Basket basket) {

       Basket basketUpdate = basketRepository.findById(id).orElseThrow(()->  new ApiException("Sepet bulunamadı", HttpStatus.NOT_FOUND));
        basketUpdate.setUserId(getCurrentUser().getId());
        basketUpdate.setProducts(basket.getProducts());
        return basketRepository.save(basket);
    }

    @Override
    public Basket deleteBasket(Long id) {
        User user=getCurrentUser();
        Basket basketDelete = basketRepository.findById(id).orElseThrow(()->
                new ApiException("Sepet bulunamadı", HttpStatus.NOT_FOUND)
        );
       if(!user.getBasketList().contains(basketDelete)){
          throw new ApiException("Kullanıcıya ait sepet bulunamadı",HttpStatus.NOT_FOUND);
       }
       user.getBasketList().remove(basketDelete);
       basketRepository.delete(basketDelete);
        return basketDelete;
    }

    @Override
    public List<Basket> getBasket() {
        User user=getCurrentUser();
        if(user.getBasketList().isEmpty()){
            throw new ApiException("Sepet Boş",HttpStatus.NOT_FOUND);
        }
    return  user.getBasketList();
    }

}
