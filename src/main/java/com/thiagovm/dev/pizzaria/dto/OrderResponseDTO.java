package com.thiagovm.dev.pizzaria.dto;

import java.util.UUID;

public record OrderResponseDTO(
        UUID id,
        Double total
) {}
