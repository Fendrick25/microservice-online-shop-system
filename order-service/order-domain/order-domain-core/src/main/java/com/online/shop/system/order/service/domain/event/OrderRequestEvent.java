package com.online.shop.system.order.service.domain.event;

import com.online.shop.system.order.service.domain.valueobject.OrderAddress;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class OrderRequestEvent extends OrderEvent{
    private final UUID userID;
    private final UUID cartID;
    private final OrderAddress orderAddress;

    @Builder
    public OrderRequestEvent(UUID orderID, UUID userID, UUID cartID, OrderAddress orderAddress) {
        super(orderID);
        this.userID = userID;
        this.cartID = cartID;
        this.orderAddress = orderAddress;
    }
}
