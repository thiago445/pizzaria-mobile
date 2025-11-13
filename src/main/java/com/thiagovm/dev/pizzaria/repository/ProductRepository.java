package com.thiagovm.dev.pizzaria.repository;


import com.thiagovm.dev.pizzaria.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product,UUID> {

}
