package com.example.ecommerce.service;

import com.example.ecommerce.entity.Address;

import java.util.List;

public interface AddressService {
    Address saveAddress(Address address);
    Address updateAddress(Long id,Address address);
    Address deleteAddress(Long id);
    List<Address> getAllAddress();
    Address addAddress(Address address);
    List<Address> getUserAllAddress();
}
