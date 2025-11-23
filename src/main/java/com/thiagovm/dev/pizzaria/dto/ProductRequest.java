package com.thiagovm.dev.pizzaria.dto;

import com.thiagovm.dev.pizzaria.entity.ProductType;

public record ProductRequest(String name,
                             String description,
                             Double price,
                             ProductType tipe) {

}
