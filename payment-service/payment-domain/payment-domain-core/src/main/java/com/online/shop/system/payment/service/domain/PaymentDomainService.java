package com.online.shop.system.payment.service.domain;

import com.online.shop.system.payment.service.domain.entity.Payment;
import com.online.shop.system.payment.service.domain.event.PaymentCancelledEvent;
import com.online.shop.system.payment.service.domain.event.PaymentExpiredEvent;
import com.online.shop.system.payment.service.domain.event.PaymentPaidEvent;

import java.math.BigDecimal;

public interface PaymentDomainService {

    void initializePayment(Payment payment);
    PaymentPaidEvent payPayment(Payment payment, BigDecimal amount);
    PaymentCancelledEvent cancelPayment(Payment payment);
    PaymentExpiredEvent expirePayment(Payment payment);
}
