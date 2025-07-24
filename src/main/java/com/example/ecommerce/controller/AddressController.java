package com.example.ecommerce.controller;

import com.example.ecommerce.dto.AddressRequestDto;
import com.example.ecommerce.dto.AddressResponseDto;
import com.example.ecommerce.dto.AddressUpdateRequestDto;
import com.example.ecommerce.entity.Address;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/address")
    public List<AddressResponseDto>getAllAddress(){
        return  addressService.getUserAllAddress();
    }
    @PostMapping("/address")
    public Address saveAddress(@RequestBody AddressRequestDto addressRequestDto){

        return  addressService.addAddress(addressRequestDto);
    }
    @PutMapping("/address")
    public Address updateAddress(@RequestBody AddressUpdateRequestDto updateRequestDto){
        return  addressService.updateAddress(updateRequestDto);
    }
    @DeleteMapping("/address/{id}")
    public Address deleteAddress(@PathVariable Long id){
        return  addressService.deleteAddress(id);
    }
}
