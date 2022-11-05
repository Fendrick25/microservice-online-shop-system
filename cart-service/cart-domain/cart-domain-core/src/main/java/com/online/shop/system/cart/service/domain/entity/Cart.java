package com.online.shop.system.cart.service.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class Cart {

    private UUID id;
    private final UUID userID;
    private final List<CartItem> items;
    private BigDecimal totalPrice;

    public void initializeCart(){
        id = UUID.randomUUID();
    }

    public void calculateTotalPrice(){
        if(items.isEmpty())
            totalPrice = BigDecimal.ZERO;

        items.forEach(CartItem::calculateSubTotal);
        totalPrice = items.stream().map(CartItem::getSubTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
