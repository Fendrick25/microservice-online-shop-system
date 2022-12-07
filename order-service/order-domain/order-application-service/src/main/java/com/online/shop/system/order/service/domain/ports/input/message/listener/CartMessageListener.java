package com.online.shop.system.order.service.domain.ports.input.message.listener;

import com.online.shop.system.order.service.domain.dto.message.CartOrderResponse;

public interface CartMessageListener {

    void createOrder(CartOrderResponse cartOrderResponse);
}
