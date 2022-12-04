package com.online.shop.system.order.service.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class OrderItem {


    private Integer id;
    private final Product product;
    private final int quantity;
    private BigDecimal subTotal;


    void initializeOrderItem(Integer id){
        this.id = id;
    }
}
