package com.thiagovm.dev.pizzaria.service;

import com.thiagovm.dev.pizzaria.entity.Product;
import com.thiagovm.dev.pizzaria.entity.ProductType;
import com.thiagovm.dev.pizzaria.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final AzureStorageService azureStorageService;

    public ProductService(ProductRepository productRepository, AzureStorageService azureStorageService) {
        this.productRepository = productRepository;
        this.azureStorageService = azureStorageService;
    }

    public Product createProduct(String name, String description, Double price, ProductType type, MultipartFile image) throws IOException {
        String imageUrl = null;
        if (image != null && !image.isEmpty()) {
            imageUrl = azureStorageService.uploadFile(image, "product-images");
        }

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setType(type);
        product.setImageUrl(imageUrl);

        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }
}

