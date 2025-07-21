package com.example.ecommerce.service;

import com.example.ecommerce.dto.AddressRequestDto;
import com.example.ecommerce.entity.Address;

import java.util.List;

public interface AddressService {

    Address updateAddress(Long id,AddressRequestDto address);
    Address deleteAddress(Long id);
    List<Address> getAllAddress();
    Address addAddress(AddressRequestDto addressRequestDto);
    List<Address> getUserAllAddress();
}
