package com.online.shop.system.service.domain.ports.output.repository;

import com.online.shop.system.product.service.domain.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {
    Product createProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(UUID productID);
    Product getProduct(UUID productID);
    List<Product> getAllProduct();
    List<Product> getProductByCategory(UUID categoryID, int page, int size);
    List<Product> searchProduct(String productName, int page, int size);
}
