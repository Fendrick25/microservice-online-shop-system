package com.online.shop.system.order.service.domain.dto.create;

import com.online.shop.system.order.service.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class GetOrderByStatus {

    private final UUID userID;
    private final OrderStatus orderStatus;
}
