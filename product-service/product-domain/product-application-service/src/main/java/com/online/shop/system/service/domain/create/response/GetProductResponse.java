package com.online.shop.system.service.domain.create.response;

import com.online.shop.system.product.service.domain.entity.Category;
import com.online.shop.system.product.service.domain.valueobject.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class GetProductResponse {
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
}
