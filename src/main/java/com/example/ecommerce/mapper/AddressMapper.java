package com.example.ecommerce.mapper;
import com.example.ecommerce.dto.AddressRequestDto;
import com.example.ecommerce.dto.ProductRequestDto;
import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address toEntity(AddressRequestDto addressRequestDto){
        Address address = new Address();
        address.setAddressDetail(addressRequestDto.addressDetail());
        address.setName(addressRequestDto.name());
        address.setSurname(addressRequestDto.surname());
        address.setCity(addressRequestDto.city());
        address.setDistrict(addressRequestDto.district());
        address.setNeighborhood(addressRequestDto.neighborhood());
        address.setTitle(addressRequestDto.title());
        address.setPhoneNumber(addressRequestDto.phoneNumber());
        return address;
    }
}
