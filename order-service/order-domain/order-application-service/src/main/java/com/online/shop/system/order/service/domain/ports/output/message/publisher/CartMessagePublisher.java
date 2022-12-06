package com.online.shop.system.order.service.domain.ports.output.message.publisher;

import com.online.shop.system.order.service.domain.dto.create.CreateOrder;

public interface CartMessagePublisher {
    void publish(CreateOrder createOrder);
}
