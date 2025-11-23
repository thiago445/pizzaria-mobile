package com.thiagovm.dev.pizzaria.dto;

import com.thiagovm.dev.pizzaria.entity.PaymentMethod;

import java.util.List;

public record OrderRequestDTO(
        String street,
        String numberHouse,
        String complement,
        String neighborhood,
        String city,
        String state,
        PaymentMethod paymentMethod,
        List<orderItemRequestDTO> items
) {}
