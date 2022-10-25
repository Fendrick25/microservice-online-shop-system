package com.online.shop.system.product.service.domain;

import com.online.shop.system.product.service.domain.entity.Category;
import com.online.shop.system.product.service.domain.entity.Product;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ProductDomainServiceImpl implements ProductDomainService{
    @Override
    public Product validateProduct(Product product) {
        product.initializeProduct();
        log.info("Product with id: {} is initiated", product.getProductID());
        return product;
    }

    @Override
    public Category validateCategory(Category category) {
        log.info("Category with id: {} is initiated", category.getCategoryID());
        return category;
    }
}
