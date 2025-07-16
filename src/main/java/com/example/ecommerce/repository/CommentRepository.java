package com.example.ecommerce.repository;

import com.example.ecommerce.entity.ProductComment;
import com.example.ecommerce.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<ProductComment,Long> {

    @Query("SELECT pc FROM ProductComment pc WHERE pc.user.id = :userId AND pc.product.id = :productId")
    Optional<ProductComment> findByUserIdAndProductId(
            @Param("userId") Long userId,
            @Param("productId") Long productId
    );

    @Query("SELECT pc FROM ProductComment pc WHERE pc.product.id = :productId")
    List<ProductComment> findProductIdGetComments(@Param("productId") Long productId);

    @Query("SELECT pc FROM ProductComment pc WHERE pc.user.id = :userId AND pc.id = :commentId")
    Optional<ProductComment> findByUserIdAndCommentId(
            @Param("userId") Long userId,
            @Param("commentId") Long commentId
    );
}
