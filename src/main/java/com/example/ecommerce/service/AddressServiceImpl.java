package com.example.ecommerce.service;

import com.example.ecommerce.entity.Address;
import com.example.ecommerce.exceptions.ApiException;
import com.example.ecommerce.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Long id, Address address) {
        Optional<Address> addressOptional= addressRepository.findById(id);
        Address addressold= addressOptional.orElseThrow(()->new ApiException("Adres bulunamadı", HttpStatus.NOT_FOUND));
        addressRepository.save(address);
        return addressold;
    }

    @Override
    public Address deleteAddress(Long id) {
        Optional<Address> addressOptional= addressRepository.findById(id);
        Address addressold= addressOptional.orElseThrow(()->new ApiException("Adres bulunamadı", HttpStatus.NOT_FOUND));
        addressRepository.delete(addressold);
        return addressold;
    }

    @Override
    public List<Address> getAllAddress() {
        if(addressRepository.findAll().isEmpty()){
            throw new ApiException("Hiç adres yok",HttpStatus.NOT_FOUND);
        }
        return addressRepository.findAll();
    }
}
