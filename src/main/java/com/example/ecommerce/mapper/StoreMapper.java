package com.example.ecommerce.mapper;
import com.example.ecommerce.dto.StoreRequestDto;
import com.example.ecommerce.entity.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {
    public Store toEntity(StoreRequestDto storeRequestDto){
        Store store = new Store();
        store.setName(storeRequestDto.name());
        store.setPhone(storeRequestDto.phone());
        store.setTaxNo(storeRequestDto.taxNo());
        store.setBankAccount(storeRequestDto.bankAccount());

        return store;
    }
}
