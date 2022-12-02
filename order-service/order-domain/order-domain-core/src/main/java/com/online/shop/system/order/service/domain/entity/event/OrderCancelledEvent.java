package com.online.shop.system.order.service.domain.entity.event;

import com.online.shop.system.order.service.domain.entity.Order;
import lombok.Builder;

import java.time.ZonedDateTime;

public class OrderCancelledEvent extends OrderEvent{
    @Builder
    public OrderCancelledEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
