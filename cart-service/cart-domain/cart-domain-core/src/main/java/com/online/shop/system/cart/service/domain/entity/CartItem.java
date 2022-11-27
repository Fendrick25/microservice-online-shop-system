package com.online.shop.system.cart.service.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CartItem {
    private final Integer id;
    private final UUID cartID;

    @Setter
    private Product product;

    @Setter
    private int quantity;
    private BigDecimal subTotal;

    public void calculateSubTotal(){
        subTotal = product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
