package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.ProductRequestDto;
import com.example.ecommerce.dto.ProductResponseDto;
import com.example.ecommerce.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponseDto toResponse(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto(product.getId(),product.getName(), product.getDescription(),
                product.getPrice(), product.getStock(),product.getCategory().getId(),product.getRating(),product.getSellCount(),product.getSellerId(),
                product.getProductImages(),
                product.getProductComments());
        return  productResponseDto;
    }

    public Product toEntity(ProductRequestDto productRequestDto){
        Product product = new Product();
        product.setName(productRequestDto.name());
        product.setPrice(productRequestDto.price());
        product.setCategory(productRequestDto.categoryName());
        product.setStock(productRequestDto.stock());
        product.setSellerId(productRequestDto.sellerId());
        return product;
    }

}
