package com.example.ecommerce.service;

import com.example.ecommerce.dto.AddressRequestDto;
import com.example.ecommerce.dto.AddressResponseDto;
import com.example.ecommerce.dto.AddressUpdateRequestDto;
import com.example.ecommerce.entity.Address;

import java.util.List;

public interface AddressService {

    Address updateAddress(AddressUpdateRequestDto updateRequestDto);
    Address deleteAddress(Long id);
    List<Address> getAllAddress();
    Address addAddress(AddressRequestDto addressRequestDto);
    List<AddressResponseDto> getUserAllAddress();
}
