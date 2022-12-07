package com.online.shop.system.order.service.domain.dto.create;

import com.online.shop.system.order.service.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderByStatus {

    private UUID userID;
    private OrderStatus orderStatus;
}
