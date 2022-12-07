package com.online.shop.system.order.service.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public abstract class OrderEvent{
    private final UUID orderID;
}
