package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductResponseCommentDto;
import com.example.ecommerce.dto.ProductRequestDto;
import com.example.ecommerce.dto.ProductResponseDto;
import com.example.ecommerce.entity.ProductComment;
import com.example.ecommerce.service.CommentService;
import com.example.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private final ProductService productService;
    @Autowired
    private  final CommentService commentService;
    @GetMapping()
    public List<ProductResponseDto> getAllProducts(@PageableDefault(size = 25) Pageable pageable){
        return productService.getAllProducts(pageable);
    }
    @PostMapping()
    public ProductResponseDto createProduct(@RequestBody@Validated ProductRequestDto productRequestDto){
        return productService.createProduct(productRequestDto);
    }

    @GetMapping("{id}/comments")
    public List<ProductResponseCommentDto> getProductComments(@PathVariable Long id){
        return commentService.productGetAllComments(id);
    }
    @PostMapping("{id}")
    public ProductResponseCommentDto addComment(@RequestBody@Validated ProductComment comment,@PathVariable Long id){
        return commentService.addComment(comment,id);
    }
    @DeleteMapping()
    public ProductResponseCommentDto deleteComment( @RequestBody@Validated Long id){
        return commentService.deleteComment(id);
    }

    @PutMapping("{id}")
    public ProductResponseCommentDto updateComment(@RequestBody@Validated String updateComment,@PathVariable Long id){
      return  commentService.updateComment(updateComment,id);
    }

    @PostMapping("/all")
    public ResponseEntity<String> syncProducts() {
        productService.allFetch();
        return ResponseEntity.ok("Ürünler başarıyla local'e aktarıldı.");
    }
}
