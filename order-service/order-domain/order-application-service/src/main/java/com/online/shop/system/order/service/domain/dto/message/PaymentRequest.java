package com.online.shop.system.order.service.domain.dto.message;

import com.online.shop.system.order.service.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class PaymentRequest {
    private UUID userID;
    private UUID orderID;
    private BigDecimal price;
    private OrderStatus orderStatus;
}
