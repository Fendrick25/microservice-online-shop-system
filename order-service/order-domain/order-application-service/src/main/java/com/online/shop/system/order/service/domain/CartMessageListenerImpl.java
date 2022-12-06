package com.online.shop.system.order.service.domain;

import com.online.shop.system.order.service.domain.dto.message.CartOrderResponse;
import com.online.shop.system.order.service.domain.ports.input.message.listener.CartMessageListener;
import com.online.shop.system.order.service.domain.ports.input.service.OrderApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartMessageListenerImpl implements CartMessageListener {
    private final OrderApplicationService orderApplicationService;

    @Override
    public void createOrder(CartOrderResponse createOrder) {
        orderApplicationService.createOrder(createOrder);
    }
}
