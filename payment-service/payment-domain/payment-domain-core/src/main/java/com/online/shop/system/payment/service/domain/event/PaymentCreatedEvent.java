package com.online.shop.system.payment.service.domain.event;

import com.online.shop.system.payment.service.domain.valueobject.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
public class PaymentCreatedEvent extends PaymentEvent{

    private final ZonedDateTime purchasedAt;

    @Builder
    public PaymentCreatedEvent(UUID paymentID, UUID userID, UUID orderID, PaymentStatus paymentStatus, ZonedDateTime purchasedAt) {
        super(paymentID, userID, orderID, paymentStatus);
        this.purchasedAt = purchasedAt;
    }
}
