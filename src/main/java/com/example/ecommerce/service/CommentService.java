package com.example.ecommerce.service;
import com.example.ecommerce.dto.ProductResponseCommentDto;
import com.example.ecommerce.entity.ProductComment;
import java.util.List;

public interface CommentService {
    List<ProductResponseCommentDto>  productGetAllComments(Long id);
    ProductResponseCommentDto deleteComment(Long commentId);
    ProductResponseCommentDto updateComment(String updateComment,Long productId);
    ProductResponseCommentDto addComment(ProductComment comment,Long productId);
}
