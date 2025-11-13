package com.thiagovm.dev.pizzaria.repository;

import com.thiagovm.dev.pizzaria.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

}
