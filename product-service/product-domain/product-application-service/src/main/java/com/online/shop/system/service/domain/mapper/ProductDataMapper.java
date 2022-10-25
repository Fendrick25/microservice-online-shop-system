package com.online.shop.system.service.domain.mapper;

import com.online.shop.system.product.service.domain.entity.Category;
import com.online.shop.system.product.service.domain.entity.Product;
import com.online.shop.system.service.domain.create.CreateProduct;
import com.online.shop.system.service.domain.create.response.CreateProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductDataMapper {
    public Product createProductToProduct(CreateProduct createProduct){
        return Product.builder()
                .name(createProduct.getName())
                .description(createProduct.getDescription())
                .price(createProduct.getPrice())
                .quantity(createProduct.getQuantity())
                .category(createProduct.getCategory())
                .build();
    }

    public CreateProductResponse productToCreateProductResponse(Product product){
        return CreateProductResponse.builder()
                .productID(product.getProductID())
                .build();
    }
}
