package com.thiagovm.dev.pizzaria.controller;



import com.thiagovm.dev.pizzaria.entity.Order;
import com.thiagovm.dev.pizzaria.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) { this.orderService = orderService; }

    @PostMapping("/{userId}")
    public ResponseEntity<Order> createOrder(@PathVariable UUID userId, @RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrder(userId, order));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable UUID userId) {
        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }
}

