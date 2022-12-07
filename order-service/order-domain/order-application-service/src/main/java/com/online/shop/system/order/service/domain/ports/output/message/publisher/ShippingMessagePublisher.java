package com.online.shop.system.order.service.domain.ports.output.message.publisher;

import com.online.shop.system.order.service.domain.event.OrderPaidEvent;

public interface ShippingMessagePublisher {
    void shipOrder(OrderPaidEvent orderPaidEvent);
}
