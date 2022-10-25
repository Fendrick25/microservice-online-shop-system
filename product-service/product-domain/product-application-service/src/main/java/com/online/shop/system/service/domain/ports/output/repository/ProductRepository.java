package com.online.shop.system.service.domain.ports.output.repository;

import com.online.shop.system.product.service.domain.entity.Category;
import com.online.shop.system.product.service.domain.entity.Product;

public interface ProductRepository {
    Product createProduct(Product product);
    Category createCategory(Category category);
}
