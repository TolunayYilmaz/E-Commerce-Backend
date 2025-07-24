package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product",schema = "ecommerce")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer stock;
    private String description;
    private Double rating;
    private Integer sellCount;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    private  Category category;

    @Column(name = "seller_id")
    private Long sellerId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductImage> productImages;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductComment> productComments;

}
