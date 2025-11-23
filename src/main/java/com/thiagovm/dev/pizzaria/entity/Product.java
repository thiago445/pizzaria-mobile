package com.thiagovm.dev.pizzaria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy =GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private Double price;
    @Enumerated(EnumType.STRING)
    private ProductType type; // SWEET, SAVORY, DRINK

    private String imageUrl;
}


