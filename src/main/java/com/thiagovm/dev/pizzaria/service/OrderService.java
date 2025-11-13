package com.thiagovm.dev.pizzaria.service;


import com.thiagovm.dev.pizzaria.entity.Order;
import com.thiagovm.dev.pizzaria.entity.Product;
import com.thiagovm.dev.pizzaria.entity.User;
import com.thiagovm.dev.pizzaria.repository.OrderRepository;
import com.thiagovm.dev.pizzaria.repository.ProductRepository;
import com.thiagovm.dev.pizzaria.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(UUID userId, Order order) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        order.setUser(user);

        double total = order.getItems().stream().mapToDouble(item -> {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

            item.setOrder(order);
            item.setPrice(product.getPrice() * item.getQuantity());

            return item.getPrice();
        }).sum();

        order.setTotalValue(total);

        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(UUID userId) {
        return orderRepository.findByUserId(userId);
    }
}

