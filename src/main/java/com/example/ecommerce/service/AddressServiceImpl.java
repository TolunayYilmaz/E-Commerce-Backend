package com.example.ecommerce.service;

import com.example.ecommerce.dto.AddressRequestDto;
import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exceptions.ApiException;
import com.example.ecommerce.mapper.AddressMapper;
import com.example.ecommerce.repository.AddressRepository;

import com.example.ecommerce.service.parent.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class AddressServiceImpl extends BaseService implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;
    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }
    @Override
    public List<Address> getAllAddress() {
        if(addressRepository.findAll().isEmpty()){
            throw new ApiException("Hiç adres yok",HttpStatus.NOT_FOUND);
        }
        return addressRepository.findAll();
    }
    @Override
    public List<Address> getUserAllAddress() {
        User user=getCurrentUser();
       return user.getAddressList();
    }
    @Override
    public Address deleteAddress(Long id) {
        User user = getCurrentUser(); // Giriş yapan kullanıcıyı al
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ApiException("Adres bulunamadı", HttpStatus.NOT_FOUND));

        // Bu adres giriş yapan kullanıcıya mı ait? Kontrol et
        if (!user.getAddressList().contains(address)) {
            throw new ApiException("Bu adrese silme yetkiniz yok", HttpStatus.FORBIDDEN);
        }

        // Adresi sil
        user.getAddressList().remove(address); // ilişkiden çıkar
        addressRepository.delete(address);     // DB'den sil
        return address;
    }

    @Override
    public Address updateAddress(Long id, AddressRequestDto updatedAddress) {
        User user = getCurrentUser();

        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new ApiException("Adres bulunamadı", HttpStatus.NOT_FOUND));

        // Bu adres kullanıcıya ait mi? Kontrol et
        if (!user.getAddressList().contains(existingAddress)) {
            throw new ApiException("Bu adrese güncelleme yetkiniz yok", HttpStatus.FORBIDDEN);
        }

        // Alanları tek tek güncelle
        existingAddress.setTitle(updatedAddress.title() != null ? updatedAddress.title() : existingAddress.getTitle());
        existingAddress.setName(updatedAddress.name()!= null ? updatedAddress.name():existingAddress.getTitle());
        existingAddress.setSurname(updatedAddress.surname()!= null ? updatedAddress.surname():existingAddress.getSurname());
        existingAddress.setPhoneNumber(updatedAddress.phoneNumber()!= null ? updatedAddress.phoneNumber():existingAddress.getPhoneNumber());
        existingAddress.setCity(updatedAddress.city()!= null ? updatedAddress.city():existingAddress.getCity());
        existingAddress.setDistrict(updatedAddress.district()!= null ? updatedAddress.district():existingAddress.getDistrict());
        existingAddress.setNeighborhood(updatedAddress.neighborhood()!= null ? updatedAddress.neighborhood():existingAddress.getDistrict());
        existingAddress.setAddressDetail(updatedAddress.addressDetail()!= null ?updatedAddress.addressDetail(): existingAddress.getAddressDetail());

        return addressRepository.save(existingAddress);
    }

    @Override
    public Address addAddress(AddressRequestDto addressRequestDto) {
        User user=getCurrentUser();
        Address address=addressMapper.toEntity(addressRequestDto);
        user.getAddressList().add(address);
        userRepository.save(user);
        return address;
    }

}
