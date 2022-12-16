package com.online.shop.system.payment.service.domain.event;

import com.online.shop.system.payment.service.domain.valueobject.PaymentMessage;
import com.online.shop.system.payment.service.domain.valueobject.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PaymentExpiredEvent extends PaymentEvent{
    private final PaymentMessage paymentMessage;

    @Builder
    public PaymentExpiredEvent(UUID paymentID, UUID userID, UUID orderID, PaymentStatus paymentStatus) {
        super(paymentID, userID, orderID, paymentStatus);
        this.paymentMessage = PaymentMessage.EXPIRED;
    }
}
