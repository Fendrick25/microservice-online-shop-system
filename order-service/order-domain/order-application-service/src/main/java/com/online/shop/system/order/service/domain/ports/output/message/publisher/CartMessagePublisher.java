package com.online.shop.system.order.service.domain.ports.output.message.publisher;

import com.online.shop.system.order.service.domain.event.OrderRequestEvent;

public interface CartMessagePublisher {
    void publish(OrderRequestEvent orderRequestEvent);
}
