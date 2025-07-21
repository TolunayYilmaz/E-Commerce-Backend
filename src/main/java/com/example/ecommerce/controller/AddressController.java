package com.example.ecommerce.controller;

import com.example.ecommerce.dto.AddressRequestDto;
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
    public List<Address> getAllAddress(){
        return  addressService.getUserAllAddress();
    }
    @PostMapping("/address")
    public Address saveAddress(@RequestBody AddressRequestDto addressRequestDto){

        return  addressService.addAddress(addressRequestDto);
    }
    @PutMapping("/address/{id}")
    public Address updateAddress(@PathVariable Long id,@RequestBody AddressRequestDto address){
        return  addressService.updateAddress(id,address);
    }
    @DeleteMapping("/address/{id}")
    public Address deleteAddress(@PathVariable Long id){
        return  addressService.deleteAddress(id);
    }
}
