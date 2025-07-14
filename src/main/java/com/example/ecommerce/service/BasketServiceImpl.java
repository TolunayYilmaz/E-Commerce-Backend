package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductResponseDto;
import com.example.ecommerce.entity.Basket;
import com.example.ecommerce.entity.CreditCard;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exceptions.ApiException;
import com.example.ecommerce.repository.BasketRepository;
import com.example.ecommerce.repository.UserRepository;
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
public class BasketServiceImpl implements BasketService {
    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;
    @Override
    public Basket saveBasket(Basket basket) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();  // Basic Auth kullanıcı adı (email)
        User user = userRepository.finbyEmail(userEmail)
                .orElseThrow(() -> new ApiException("Kullanıcı bulunamadı", HttpStatus.NOT_FOUND));
        basket.setUserId(user.getId());
        return basketRepository.save(basket);
    }

    @Override
    public Basket updateBasket(Long id ,Basket basket) {
        Optional<Basket> basketOptional = basketRepository.findById(id);
        basketOptional.orElseThrow(()->
                new ApiException("Kart bulunamadı", HttpStatus.NOT_FOUND)
        );
        return basketRepository.save(basket);
    }

    @Override
    public Basket deleteBasket(Long id) {
        Optional<Basket> basketOptional = basketRepository.findById(id);
       Basket basket= basketOptional.orElseThrow(()->
                new ApiException("Kart bulunamadı", HttpStatus.NOT_FOUND)
        );
       basketRepository.delete(basket);
        return basket;
    }

    @Override
    public List<Basket> getBasket() {
    return  basketRepository.findAll();
    }
}
