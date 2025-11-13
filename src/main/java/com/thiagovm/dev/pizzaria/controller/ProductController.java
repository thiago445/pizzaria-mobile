package com.thiagovm.dev.pizzaria.controller;




import com.thiagovm.dev.pizzaria.entity.Product;
import com.thiagovm.dev.pizzaria.entity.ProductType;
import com.thiagovm.dev.pizzaria.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) { this.productService = productService; }

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestPart("name") String name,
            @RequestPart("description") String description,
            @RequestPart("price") Double price,
            @RequestPart("type") ProductType type,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException {
        return ResponseEntity.ok(productService.createProduct(name, description, price, type, image));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }
}

