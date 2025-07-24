package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.ProductImage;
import com.example.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;
@Component

public class FetchMapper {

    public Product mapToEntity(ProductDto dto, CategoryRepository categoryRepository) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setSellerId(dto.getSellerId());
        product.setDescription(dto.getDescription());
        product.setRating(dto.getRating());
        product.setSellerId(dto.getSellerId());
        product.setSellCount(dto.getSellCount());
        product.setActive(true); // default
        product.setCategory(categoryRepository.findById(dto.getCategoryId()).orElse(null));

        List<ProductImage> imageList = dto.getImages().stream().map(imgDto -> {
            ProductImage img = new ProductImage();
            img.setUrl(imgDto.getUrl());
            img.setIndex(imgDto.getIndex());
            return img;
        }).collect(Collectors.toList());

        product.setProductImages(imageList);

        return product;
    }


    public Category mapToCategoryEntity(CategoryDto dto) {
        Category category = new Category();
        category.setCode(dto.getCode());
        category.setTitle(dto.getTitle());
        category.setImg(dto.getImg());
        category.setRating(dto.getRating());
        category.setGender(dto.getGender());
        return category;
    }
}
