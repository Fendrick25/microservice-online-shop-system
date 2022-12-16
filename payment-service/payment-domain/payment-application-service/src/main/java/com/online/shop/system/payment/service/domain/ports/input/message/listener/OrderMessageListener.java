package com.online.shop.system.payment.service.domain.ports.input.message.listener;

import com.online.shop.system.payment.service.domain.dto.message.CancelPayment;
import com.online.shop.system.payment.service.domain.dto.message.PaymentRequest;

public interface OrderMessageListener {

    void createPayment(PaymentRequest paymentRequest);
    void cancelPayment(CancelPayment cancelPayment);
}
