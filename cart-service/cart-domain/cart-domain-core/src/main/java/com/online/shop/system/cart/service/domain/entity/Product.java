package com.online.shop.system.cart.service.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class Product {

    private final UUID id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final String imageUrl;
}
