package com.thiagovm.dev.pizzaria.dto;


import java.util.UUID;

public record OrderListItemDTO(
        UUID id,
        String customerName,
        Double totalValue
) {}
