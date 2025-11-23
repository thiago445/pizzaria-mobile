package com.thiagovm.dev.pizzaria.service;

import com.thiagovm.dev.pizzaria.dto.ProductRequest;
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

    public void createProduct(ProductRequest productRequest, MultipartFile image) throws IOException {
        String imageUrl = null;
        if (image != null && !image.isEmpty()) {
            imageUrl = azureStorageService.uploadFile(image, "product-images");
        }

        Product product = new Product();
        product.setName(productRequest.name());
        product.setDescription(productRequest.description());
        product.setPrice(productRequest.price());
        product.setType(productRequest.tipe());
        product.setImageUrl(imageUrl);
        productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }
}

