package com.online.shop.system.order.service.domain.entity.event;

import com.online.shop.system.order.service.domain.entity.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
public abstract class OrderEvent {

    private final Order order;
    private final ZonedDateTime createdAt;
}
