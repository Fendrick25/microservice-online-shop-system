package com.online.shop.system.product.service.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class Category {

    private UUID categoryID;
    private final String name;

    public void initializeCategory(){
        categoryID = UUID.randomUUID();
    }
}
