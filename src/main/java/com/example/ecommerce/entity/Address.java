package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address",schema = "ecommerce")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String name;
    private String surname;
    @Column(name = "phone_number")
    private Integer phoneNumber;
    private String city;
    private String district;
    private String neighborhood;
    @Column(name = "address_detail")
    private String addressDetail;

}
