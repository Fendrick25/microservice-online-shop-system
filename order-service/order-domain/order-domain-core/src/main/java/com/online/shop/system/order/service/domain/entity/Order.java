package com.online.shop.system.order.service.domain.entity;

import com.online.shop.system.order.service.domain.valueobject.OrderAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Order {

    private UUID id;
    private final UUID userID;
    private final List<OrderItem> items;
    private final BigDecimal totalPrice;
    private ZonedDateTime purchaseDate;
    private final OrderAddress address;
    private List<OrderDetail> details;

    public void initializeOrder(Order order){
        id = UUID.randomUUID();
        purchaseDate = ZonedDateTime.now(ZoneId.of("UTC"));
    }

}
