package com.online.shop.system.order.service.domain.event;

import com.online.shop.system.order.service.domain.valueobject.OrderAddress;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class OrderPaidEvent extends OrderEvent{
    private final OrderAddress orderAddress;

    @Builder
    public OrderPaidEvent(UUID orderID, OrderAddress orderAddress) {
        super(orderID);
        this.orderAddress = orderAddress;
    }
}
