package com.online.shop.system.cart.service.domain.ports.output.message.publisher;

import com.online.shop.system.cart.service.domain.dto.message.CartOrderResponse;
public interface OrderMessagePublisher {
    void publish(CartOrderResponse cartOrderResponse);
}
