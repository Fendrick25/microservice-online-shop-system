package com.online.shop.system.order.service.domain.event;

import com.online.shop.system.order.service.domain.entity.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
public class OrderEvent {

    private final Order order;
    private final ZonedDateTime createdAt;
}
