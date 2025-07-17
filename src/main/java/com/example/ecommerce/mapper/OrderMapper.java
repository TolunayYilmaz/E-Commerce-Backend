package com.example.ecommerce.mapper;
import com.example.ecommerce.dto.OrderRequestDto;
import com.example.ecommerce.dto.OrderResponseDto;
import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.CreditCard;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.exceptions.ApiException;
import com.example.ecommerce.repository.AddressRepository;
import com.example.ecommerce.repository.CreditCardRepository;
import com.example.ecommerce.service.parent.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OrderMapper extends BaseService {
    @Autowired
    private final AddressRepository addressRepository;
    @Autowired
    private final CreditCardRepository creditCardRepository;


    public OrderResponseDto toResponse(Order order){
        OrderResponseDto orderResponseDto = new OrderResponseDto(
                order.getId(),
                order.getUser().getId(),
                order.getAddress().getId(),
                LocalDateTime.now(),
                order.getCreditCard().getCardNumber(),
                order.getCreditCard().getCardName(),
                order.getCreditCard().getMonth(),
                order.getCreditCard().getYear(),
                order.getTotalPrice(),
                order.getOrderItems());
        return  orderResponseDto;
    }
    public Order toEntity(OrderRequestDto orderRequestDto) {
        Order order = new Order();


        Address address = addressRepository.findById(orderRequestDto.addressId())
                .orElseThrow(() -> new ApiException("Adres bulunamadı", HttpStatus.NOT_FOUND));
        order.setAddress(address);

        // Kartı oluştur ve/veya veritabanından getir
        CreditCard card = creditCardRepository.getCreditCard(orderRequestDto.cardNo()).orElseThrow(()->new ApiException("Kredi kartı bulunamadı",HttpStatus.NOT_FOUND));

        order.setCreditCard(card);
        order.setOrderDate(orderRequestDto.orderDate() != null ? orderRequestDto.orderDate() : LocalDateTime.now());
        order.setUser(getCurrentUser());
        order.setTotalPrice(orderRequestDto.totalPrice());
        order.setOrderItems(orderRequestDto.orderItems());

        return order;
    }

}
