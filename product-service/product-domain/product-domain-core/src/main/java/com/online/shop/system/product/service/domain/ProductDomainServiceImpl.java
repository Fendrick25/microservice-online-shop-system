package com.online.shop.system.product.service.domain;

import com.online.shop.system.product.service.domain.entity.Product;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ProductDomainServiceImpl implements ProductDomainService{
    @Override
    public Product validateProduct(Product product) {
        product.initializeProduct();
        log.info("Product with id: {} initiated", product.getProductID());
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        product.validateUpdateProduct();
        log.info("Product with id: {} validated", product.getProductID());
        return product;
    }

}
