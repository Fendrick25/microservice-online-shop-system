package com.online.shop.system.product.service.domain;

import com.online.shop.system.product.service.domain.entity.Category;
import com.online.shop.system.product.service.domain.entity.Product;

public interface ProductDomainService {
    Product validateProduct(Product product);
    Category validateCategory(Category category);
}
