package com.thiagovm.dev.pizzaria.dto;


import java.util.List;
import java.util.UUID;

public record OrderDetailsDTO(
        UUID id,
        String buyerName,
        String buyerEmail,
        String paymentMethod,
        Double total,
        List<OrderDetailsItemDTO> items
) {}

