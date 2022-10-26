package com.online.shop.system.product.service.domain;

import com.online.shop.system.product.service.domain.entity.Product;

public interface ProductDomainService {
    Product validateProduct(Product product);
    Product updateProduct(Product product);
}
