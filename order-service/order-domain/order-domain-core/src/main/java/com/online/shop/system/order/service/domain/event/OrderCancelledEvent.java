package com.online.shop.system.order.service.domain.event;

import com.online.shop.system.order.service.domain.valueobject.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class OrderCancelledEvent extends OrderEvent{
    private final UUID userID;
    private final OrderStatus orderStatus;
    @Builder
    public OrderCancelledEvent(UUID orderID, UUID userID, OrderStatus orderStatus) {
        super(orderID);
        this.userID = userID;
        this.orderStatus = orderStatus;
    }
}
