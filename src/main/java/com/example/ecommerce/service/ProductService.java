package com.example.ecommerce.service;
import com.example.ecommerce.dto.ProductRequestDto;
import com.example.ecommerce.dto.ProductResponseDto;
import com.example.ecommerce.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getAllProducts(Pageable pageable);
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);
    void allFetch();
    Product getProductById(Long id);
}
