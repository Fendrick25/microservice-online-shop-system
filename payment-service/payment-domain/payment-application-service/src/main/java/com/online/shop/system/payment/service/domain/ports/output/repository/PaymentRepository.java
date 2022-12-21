package com.online.shop.system.payment.service.domain.ports.output.repository;

import com.online.shop.system.payment.service.domain.entity.Payment;

import java.util.UUID;

public interface PaymentRepository {
    Payment createPayment(Payment payment);
    Payment getPayment(UUID paymentID);
    void updatePayment(Payment payment);
    Payment getPaymentByOrderID(UUID orderID);
}
