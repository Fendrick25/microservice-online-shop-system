package com.online.shop.system.payment.service.domain.dto.message;

import com.online.shop.system.payment.service.domain.valueobject.PaymentStatus;
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
    private UUID userID;
    private BigDecimal price;
    private PaymentStatus paymentStatus;
}
