package com.online.shop.system.product.service.domain.dto.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class ProductOverview {
    private final UUID productID;
    private final String name;
    private final BigDecimal price;
    private final int quantity;
    private final int soldAmount;
    private final Double rating;
    private final String imageUrl;
}
