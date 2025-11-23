package com.thiagovm.dev.pizzaria.controller;




import com.thiagovm.dev.pizzaria.dto.ProductRequest;
import com.thiagovm.dev.pizzaria.entity.Product;

import com.thiagovm.dev.pizzaria.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) { this.productService = productService; }

    @PostMapping
    public ResponseEntity<Void> createProduct(
            @RequestPart("data") ProductRequest productRequest,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException {
        productService.createProduct(productRequest, image);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {

        return ResponseEntity.ok(productService.getAll());
    }
}

