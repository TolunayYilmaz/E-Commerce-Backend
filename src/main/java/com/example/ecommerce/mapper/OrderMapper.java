package com.example.ecommerce.mapper;
import com.example.ecommerce.dto.OrderRequestDto;
import com.example.ecommerce.dto.OrderResponseDto;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.exceptions.ApiException;
import com.example.ecommerce.repository.AddressRepository;
import com.example.ecommerce.repository.CreditCardRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.parent.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    @Autowired
    private final AddressRepository addressRepository;
    @Autowired
    private final CreditCardRepository creditCardRepository;
    @Autowired
    private  final ProductService productService;



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
        order.setTotalPrice(orderRequestDto.totalPrice());
        List<OrderItem> orderItems = orderRequestDto.products().stream()
                .map(dto -> {
                    Product product = productService.getProductById(dto.productId()); // productId'yi dto'dan al
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(product);
                    orderItem.setQuantity(dto.count());
                    orderItem.setTotalPrice(product.getPrice() * dto.count());
                    return orderItem;//total price hesaplamaları eklenecek
                })
                .toList();

        order.setOrderItems(orderItems);


        return order;
    }

}
