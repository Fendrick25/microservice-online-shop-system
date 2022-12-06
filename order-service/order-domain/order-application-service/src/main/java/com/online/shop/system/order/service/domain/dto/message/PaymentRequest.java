package com.online.shop.system.order.service.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class PaymentRequest {
    private UUID orderID;
    private BigDecimal price;
}
