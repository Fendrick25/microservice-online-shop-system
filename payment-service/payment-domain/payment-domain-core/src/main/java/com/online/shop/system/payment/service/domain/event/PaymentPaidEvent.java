package com.online.shop.system.payment.service.domain.event;

import com.online.shop.system.payment.service.domain.valueobject.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PaymentPaidEvent extends PaymentEvent{

    @Builder
    public PaymentPaidEvent(UUID paymentID, UUID userID, UUID orderID, PaymentStatus paymentStatus) {
        super(paymentID, userID, orderID, paymentStatus);
    }
}
