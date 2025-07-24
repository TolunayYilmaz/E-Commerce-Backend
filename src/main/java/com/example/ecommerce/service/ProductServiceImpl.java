package com.example.ecommerce.service;

import com.example.ecommerce.dto.*;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.ProductImage;
import com.example.ecommerce.exceptions.ApiException;
import com.example.ecommerce.mapper.FetchMapper;
import com.example.ecommerce.mapper.ProductMapper;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.domain.Pageable;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final ProductMapper productMapper;
    @Autowired
    private final RestTemplate restTemplate;
    @Autowired
    private  final CategoryRepository categoryRepository;
    @Autowired
    private  final FetchMapper fetchMapper;
//
//    @Override
//    public ProductsResponseDto getAllProducts(Long categoryId, Pageable pageable) {
//        Page<Product> productPage;
//
//        if (categoryId != null) {
//            productPage = productRepository.findByCategoryId(categoryId, pageable); // veya yukarıdaki @Query'li versiyon
//        } else {
//            productPage = productRepository.findAll(pageable);
//        }
//
//        List<ProductResponseDto> productResponseList = productPage.getContent()
//                .stream()
//                .map(productMapper::toResponse)
//                .toList();
//
//        return new ProductsResponseDto(productResponseList);
//    }

    @Override
    public ProductsResponseDto getAllProducts(Long categoryId, Pageable pageable) {
        Page<Product> productPage;

        if (categoryId != null) {
            productPage = productRepository.findByCategoryId(categoryId, pageable);
        } else {
            productPage = productRepository.findAll(pageable);
        }

        List<ProductResponseDto> productResponseList = productPage.getContent()
                .stream()
                .map(productMapper::toResponse)
                .toList();

        return new ProductsResponseDto(productResponseList);
    }


    @Override
    public Product getProductById(Long id) {

        return  productRepository.findById(id).orElseThrow(()-> new  ApiException("Ürün bulunmamaktadır", HttpStatus.NOT_FOUND));
    }
    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
       ProductResponseDto productResponseDto=  productMapper.toResponse(productRepository.save(productMapper.toEntity(productRequestDto)));
        return productResponseDto;
    }
    private void syncAllProducts() {
        String baseUrl = "https://workintech-fe-ecommerce.onrender.com/products/";
        for (long id = 2; id <= 588; id++) {
            try {
                ResponseEntity<ProductDto> response = restTemplate.getForEntity(baseUrl + id, ProductDto.class);
                ProductDto dto = response.getBody();
                if (dto != null) {
                    Product product = fetchMapper.mapToEntity(dto,categoryRepository);
                    productRepository.save(product);
                    System.out.println("Ürün kaydedildi: " + dto.getName());
                }
            } catch (HttpClientErrorException.NotFound e) {
                System.out.println("Ürün bulunamadı: ID " + id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void syncCategories() {
        String url = "https://workintech-fe-ecommerce.onrender.com/categories";

        try {
            ResponseEntity<CategoryDto[]> response = restTemplate.getForEntity(url, CategoryDto[].class);
            CategoryDto[] dtos = response.getBody();

            if (dtos != null) {
                List<Category> categories = Arrays.stream(dtos)
                        .map(fetchMapper::mapToCategoryEntity)
                        .collect(Collectors.toList());

                categoryRepository.saveAll(categories);
                System.out.println("Kategoriler başarıyla kaydedildi.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void allFetch(){
     syncCategories();
     syncAllProducts();
    }


}
