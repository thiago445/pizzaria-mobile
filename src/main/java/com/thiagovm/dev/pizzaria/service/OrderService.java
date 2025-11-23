package com.thiagovm.dev.pizzaria.service;

import com.thiagovm.dev.pizzaria.dto.*;
import com.thiagovm.dev.pizzaria.entity.*;
import com.thiagovm.dev.pizzaria.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    public OrderResponseDTO createOrder(OrderRequestDTO dto,UUID token) {
        User user =userService.findById(token);

        Order order = new Order();
        order.setStreet(dto.street());
        order.setNumber(dto.numberHouse());
        order.setComplement(dto.complement());
        order.setNeighborhood(dto.neighborhood());
        order.setCity(dto.city());
        order.setState(dto.state());
        order.setPaymentMethod(dto.paymentMethod());
        order.setUser(user);

        List<OrderItem> items = new ArrayList<>();
        double total = 0;

        for (var itemReq : dto.items()) {

            var product = productRepository.findById(itemReq.productId())
                    .orElseThrow(() -> new RuntimeException("Produto não existe"));

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemReq.quantity());
            item.setPrice(product.getPrice());

            total += product.getPrice() * itemReq.quantity();

            items.add(item);
        }

        order.setItems(items);
        order.setTotalValue(total);

        var savedOrder = orderRepository.save(order);

        return new OrderResponseDTO(
                savedOrder.getId(),
                total
        );
    }
    public List<OrderListItemDTO> listAll() {
        return orderRepository.findAll()
                .stream()
                .map(o -> new OrderListItemDTO(
                        o.getId(),
                        o.getUser().getNome(),
                        o.getTotalValue()
                ))
                .toList();
    }

    public OrderDetailsDTO getOrderDetails(UUID orderId) {

        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        var items = order.getItems()
                .stream()
                .map(i -> new OrderDetailsItemDTO(
                        i.getProduct().getId(),
                        i.getProduct().getName(),
                        i.getPrice(),
                        i.getQuantity(),
                        i.getPrice() * i.getQuantity()
                ))
                .toList();

        return new OrderDetailsDTO(
                order.getId(),
                order.getUser().getNome(),
                order.getUser().getEmail(),
                order.getPaymentMethod().name(),
                order.getTotalValue(),
                items
        );
    }
}