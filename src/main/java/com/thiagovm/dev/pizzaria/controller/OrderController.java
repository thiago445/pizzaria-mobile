package com.thiagovm.dev.pizzaria.controller;

import com.thiagovm.dev.pizzaria.dto.OrderDetailsDTO;
import com.thiagovm.dev.pizzaria.dto.OrderListItemDTO;
import com.thiagovm.dev.pizzaria.dto.OrderRequestDTO;
import com.thiagovm.dev.pizzaria.dto.OrderResponseDTO;
import com.thiagovm.dev.pizzaria.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> create(@RequestBody OrderRequestDTO dto,JwtAuthenticationToken token) {
        return ResponseEntity.ok(service.createOrder(dto,UUID.fromString(token.getToken().getSubject())));
    }
    @GetMapping
    public ResponseEntity<List<OrderListItemDTO>> listAll() {

        return ResponseEntity.ok(service.listAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailsDTO> getOrderDetails(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getOrderDetails(id));
    }


}
