package com.online.shop.system.payment.service.domain.ports.input.service;

import com.online.shop.system.payment.service.domain.dto.message.CancelPayment;
import com.online.shop.system.payment.service.domain.dto.request.PayOrder;
import com.online.shop.system.payment.service.domain.dto.request.response.PayOrderResponse;
import com.online.shop.system.payment.service.domain.entity.Payment;

import jakarta.validation.Valid;

public interface PaymentApplicationService {
    PayOrderResponse payOrder(@Valid PayOrder payOrder);
    void paymentExpired(Payment payment);
}
