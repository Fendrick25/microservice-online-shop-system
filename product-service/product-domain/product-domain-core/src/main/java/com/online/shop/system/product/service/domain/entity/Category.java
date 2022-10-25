package com.online.shop.system.product.service.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Category {

    private final Integer categoryID;
    private final String name;
}
