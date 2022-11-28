package com.online.shop.system.order.service.domain.entity;

import com.online.shop.system.order.service.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
public class OrderDetail {

    private final Integer id;
    private final OrderStatus orderStatus;
    private ZonedDateTime createdAt;
    private String message;
}
