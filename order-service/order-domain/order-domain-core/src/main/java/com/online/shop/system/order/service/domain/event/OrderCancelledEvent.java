package com.online.shop.system.order.service.domain.event;

import com.online.shop.system.order.service.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OrderCancelledEvent {
    private final UUID orderID;
    private final OrderStatus orderStatus;
}
