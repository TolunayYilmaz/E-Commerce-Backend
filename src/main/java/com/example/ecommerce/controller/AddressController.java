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
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping()
    public List<Address> getAllAddress(){
        return  addressService.getUserAllAddress();
    }
    @PostMapping()
    public Address saveAddress(@RequestBody AddressRequestDto addressRequestDto){

        return  addressService.addAddress(addressRequestDto);
    }
    @PutMapping("{id}")
    public Address updateAddress(@PathVariable Long id,@RequestBody AddressRequestDto address){
        return  addressService.updateAddress(id,address);
    }
    @DeleteMapping("{id}")
    public Address deleteAddress(@PathVariable Long id){
        return  addressService.deleteAddress(id);
    }
}
