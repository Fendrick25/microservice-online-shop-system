package com.online.shop.system.order.service.domain.entity;

import com.online.shop.system.order.service.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OrderDetail {

    private UUID orderID;
    private String id;
    private final OrderStatus orderStatus;
    private ZonedDateTime createdAt;
    @Setter
    private String message;
}
