package com.online.shop.system.order.service.domain.ports.output.message.publisher;

import com.online.shop.system.order.service.domain.event.OrderEvent;

public interface PaymentMessagePublisher {
    void publish(OrderEvent orderEvent);
}
