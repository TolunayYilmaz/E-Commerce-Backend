package com.example.ecommerce.service;

import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exceptions.ApiException;
import com.example.ecommerce.repository.AddressRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.parent.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl extends BaseService implements AddressService{

    @Autowired
    private AddressRepository addressRepository;
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
    public Address updateAddress(Long id, Address updatedAddress) {
        User user = getCurrentUser();

        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new ApiException("Adres bulunamadı", HttpStatus.NOT_FOUND));

        // Bu adres kullanıcıya ait mi? Kontrol et
        if (!user.getAddressList().contains(existingAddress)) {
            throw new ApiException("Bu adrese güncelleme yetkiniz yok", HttpStatus.FORBIDDEN);
        }

        // Alanları tek tek güncelle
        existingAddress.setTitle(updatedAddress.getTitle());
        existingAddress.setName(updatedAddress.getName());
        existingAddress.setSurname(updatedAddress.getSurname());
        existingAddress.setPhoneNumber(updatedAddress.getPhoneNumber());
        existingAddress.setCity(updatedAddress.getCity());
        existingAddress.setDistrict(updatedAddress.getDistrict());
        existingAddress.setNeighborhood(updatedAddress.getNeighborhood());
        existingAddress.setAddressDetail(updatedAddress.getAddressDetail());

        return addressRepository.save(existingAddress);
    }

    @Override
    public Address addAddress(Address address) {
        User user=getCurrentUser();
        user.getAddressList().add(address);
        userRepository.save(user);
        return address;
    }

}
