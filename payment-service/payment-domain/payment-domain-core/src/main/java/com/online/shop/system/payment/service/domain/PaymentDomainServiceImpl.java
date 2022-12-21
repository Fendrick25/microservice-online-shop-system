package com.online.shop.system.payment.service.domain;

import com.online.shop.system.payment.service.domain.entity.Payment;
import com.online.shop.system.payment.service.domain.event.PaymentCancelledEvent;
import com.online.shop.system.payment.service.domain.event.PaymentExpiredEvent;
import com.online.shop.system.payment.service.domain.event.PaymentPaidEvent;

import java.math.BigDecimal;

public class PaymentDomainServiceImpl implements PaymentDomainService{
    @Override
    public void initializePayment(Payment payment) {
        payment.initializePayment();
    }

    @Override
    public PaymentPaidEvent payPayment(Payment payment, BigDecimal amount) {
        payment.pay(amount);
        return PaymentPaidEvent.builder()
                .orderID(payment.getOrderID())
                .paymentID(payment.getId())
                .userID(payment.getUserID())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }

    @Override
    public PaymentCancelledEvent cancelPayment(Payment payment) {
        payment.cancel();
        return PaymentCancelledEvent.builder()
                .orderID(payment.getOrderID())
                .paymentID(payment.getId())
                .userID(payment.getUserID())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }

    @Override
    public PaymentExpiredEvent expirePayment(Payment payment) {
        payment.expired();
        return PaymentExpiredEvent.builder()
                .orderID(payment.getOrderID())
                .userID(payment.getUserID())
                .paymentID(payment.getId())
                .paymentStatus(payment.getPaymentStatus())
                .build();

    }
}
