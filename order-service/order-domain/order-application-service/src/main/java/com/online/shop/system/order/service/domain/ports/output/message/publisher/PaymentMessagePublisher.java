package com.online.shop.system.order.service.domain.ports.output.message.publisher;

import com.online.shop.system.order.service.domain.dto.message.PaymentRequest;
import com.online.shop.system.order.service.domain.event.OrderCancelledEvent;

public interface PaymentMessagePublisher {
    void requestPayment(PaymentRequest paymentRequest);
    void cancelPayment(OrderCancelledEvent orderCancelledEvent);
}
