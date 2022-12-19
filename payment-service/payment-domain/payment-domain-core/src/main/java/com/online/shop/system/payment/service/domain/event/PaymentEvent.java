package com.online.shop.system.payment.service.domain.event;

import com.online.shop.system.payment.service.domain.valueobject.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public abstract class PaymentEvent {
    private final UUID paymentID;
    private final UUID userID;
    private final UUID orderID;
    private final PaymentStatus paymentStatus;
}
