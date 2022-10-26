package com.online.shop.system.product.service.domain.entity;

import com.online.shop.system.product.service.domain.valueobject.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class Product {
    private UUID productID;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final int quantity;
    private ProductStatus productStatus;
    private final double rating;
    private final int soldAmount;
    private String imageUrl;
    private final Category category;

    public void initializeProduct(){
        productID = UUID.randomUUID();
        productStatus = ProductStatus.NOT_ACTIVE;
        imageUrl = "default image url";
    }

    public void validateUpdateProduct(){
        if(quantity <= 0)
            productStatus = ProductStatus.NOT_ACTIVE;
    }
}
