package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductRequestDto;
import com.example.ecommerce.dto.ProductResponseDto;
import com.example.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private final ProductService productService;
    @GetMapping()
    public List<ProductResponseDto> getAllProducts(){
        return productService.getAllProducts();
    }
    @PostMapping()
    public ProductResponseDto createProduct(@RequestBody@Validated ProductRequestDto productRequestDto){
        return productService.createProduct(productRequestDto);
    }
}
