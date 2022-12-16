package com.online.shop.system.payment.service.domain.ports.output.message.publisher;

import com.online.shop.system.payment.service.domain.event.PaymentCancelledEvent;
import com.online.shop.system.payment.service.domain.event.PaymentCreatedEvent;
import com.online.shop.system.payment.service.domain.event.PaymentExpiredEvent;
import com.online.shop.system.payment.service.domain.event.PaymentPaidEvent;

public interface OrderMessagePublisher {

    void paymentCreated(PaymentCreatedEvent paymentCreatedEvent);
    void paymentPaid(PaymentPaidEvent paymentPaidEvent);
    void paymentCancelled(PaymentCancelledEvent paymentCancelledEvent);
    void paymentExpired(PaymentExpiredEvent paymentExpiredEvent);
}
