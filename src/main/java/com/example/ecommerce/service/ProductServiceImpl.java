package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductRequestDto;
import com.example.ecommerce.dto.ProductResponseDto;
import com.example.ecommerce.mapper.ProductMapper;
import com.example.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<ProductResponseDto> productResponse=productRepository.findAll().stream().map(product -> productMapper.toResponse(product)).toList();
        return productResponse;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
       ProductResponseDto productResponseDto=  productMapper.toResponse(productRepository.save(productMapper.toEntity(productRequestDto)));
        return productResponseDto;
    }


}
