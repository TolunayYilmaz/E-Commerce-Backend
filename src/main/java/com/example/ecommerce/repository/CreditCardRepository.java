package com.example.ecommerce.repository;

import com.example.ecommerce.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {

    @Query("SELECT cc FROM CreditCard cc WHERE cc.cardNumber = :cardNumber")
    Optional<CreditCard> getCreditCard(@Param("cardNumber") String cardNumber);
}
