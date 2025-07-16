package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderResponseDto;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exceptions.ApiException;
import com.example.ecommerce.mapper.OrderMapper;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.service.parent.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends BaseService implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<OrderResponseDto> getAllOrders() {
        User user=getCurrentUser();
        if(user.getOrderList().isEmpty()){
            throw new ApiException("Kullanıcının hiç siparişi yok", HttpStatus.NOT_FOUND);
        }
        return user.getOrderList().stream().map(order -> orderMapper.toResponse(order)).toList();
    }

    @Override
    public OrderResponseDto createOrder(Order order) {

        User user=getCurrentUser();
        if(user.getOrderList().contains(order)){
            throw new ApiException("Bu ürün için zaten bir sipariş verdiniz.",HttpStatus.CONFLICT);
        }
        user.getOrderList().add(order);
        orderRepository.save(order);
        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponseDto updateOrder(Long id, Order order) {
        User user=getCurrentUser();
        Order orderUpdate=orderRepository.findById(id).orElseThrow(()->new ApiException("Güncellenicek ürün bulunamadı",HttpStatus.NOT_FOUND));
        if(!user.getOrderList().contains(orderUpdate)){
            throw new ApiException("Kullanıcıya ait Güncellenicek sipariş bulunamadı",HttpStatus.NOT_FOUND);
        }
        orderUpdate.setTotalPrice(order.getTotalPrice());
        orderUpdate.setOrderItems(order.getOrderItems());
        orderUpdate.setUser(order.getUser());
        return orderMapper.toResponse(orderRepository.save(orderUpdate));
    }

    @Override
    public OrderResponseDto deleteOrder(Long id) {
        User user=getCurrentUser();
        Order orderDelete=orderRepository.findById(id).orElseThrow(()->new ApiException("Güncellenicek ürün bulunamadı",HttpStatus.NOT_FOUND));
        if(!user.getOrderList().contains(orderDelete)){
            throw new ApiException("Kullanıcıya ait Güncellenicek sipariş bulunamadı",HttpStatus.NOT_FOUND);
        }
        user.getOrderList().remove(orderDelete);
        orderRepository.delete(orderDelete);
        return orderMapper.toResponse(orderDelete);
    }
}
