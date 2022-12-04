package com.online.shop.system.order.service.domain.dto.message;

import com.online.shop.system.order.service.domain.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class CreateOrder {
    private Order order;
}
