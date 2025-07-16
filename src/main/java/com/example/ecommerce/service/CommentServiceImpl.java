package com.example.ecommerce.service;
import com.example.ecommerce.dto.ProductResponseCommentDto;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.ProductComment;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exceptions.ApiException;
import com.example.ecommerce.repository.CommentRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.parent.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl extends BaseService implements CommentService{

    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final ProductRepository productRepository;
    @Override
    public List<ProductResponseCommentDto> productGetAllComments(Long id) {
        return commentRepository.findProductIdGetComments(id).stream()
                .map((comment)->new ProductResponseCommentDto(comment.getComment(),comment.getUser().getEmail())).toList();
    }

    @Override
    public ProductResponseCommentDto deleteComment(Long commentId) {
        User user =getCurrentUser();
       ProductComment comment=commentRepository.findByUserIdAndCommentId(user.getId(),commentId)
               .orElseThrow(()->new ApiException("Ürüne ait yorum bulunamadı", HttpStatus.NOT_FOUND));
       commentRepository.delete(comment);
        return new ProductResponseCommentDto(comment.getComment(),comment.getUser().getEmail());
    }

    @Override
    public ProductResponseCommentDto updateComment(String updateComment,Long productId) {
        User user =getCurrentUser();
        ProductComment comment=commentRepository.findByUserIdAndCommentId(user.getId(),productId)
                .orElseThrow(()->new ApiException("Ürüne ait yorum bulunamadı", HttpStatus.NOT_FOUND));
        comment.setComment(updateComment);
        return new ProductResponseCommentDto(commentRepository.save(comment).getComment(),comment.getUser().getEmail());
    }

    @Override
    public ProductResponseCommentDto addComment(ProductComment comment,Long productId) {
        User user =getCurrentUser();
        Product product= productRepository.findById(productId).orElseThrow(()->new ApiException("ürün yok",HttpStatus.NOT_FOUND));
        comment.setUser(user);
        comment.setProduct(product);
        return new ProductResponseCommentDto(commentRepository.save(comment).getComment(),comment.getUser().getEmail());
    }
}
