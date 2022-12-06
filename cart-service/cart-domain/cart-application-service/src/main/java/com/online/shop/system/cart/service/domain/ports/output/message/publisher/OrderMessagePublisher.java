package com.online.shop.system.cart.service.domain.ports.output.message.publisher;

import com.online.shop.system.cart.service.domain.entity.Cart;

public interface OrderMessagePublisher {
    void publish(Cart cart);
}
