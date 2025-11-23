package com.thiagovm.dev.pizzaria.dto;


import java.util.UUID;

public record orderItemRequestDTO(
        UUID productId,
        Integer quantity
) {}
