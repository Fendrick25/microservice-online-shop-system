package com.online.shop.system.cart.service.domain.ports.input.message.listener;

import com.online.shop.system.cart.service.domain.dto.message.CartRequest;

public interface OrderMessageListener {

    void cartRequest(CartRequest cartRequest);
}
