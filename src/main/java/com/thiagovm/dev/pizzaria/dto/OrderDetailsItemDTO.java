package com.thiagovm.dev.pizzaria.dto;


import java.util.UUID;

public record OrderDetailsItemDTO(
        UUID productId,
        String name,
        Double unitPrice,
        Integer quantity,
        Double subTotal
) {}

