package com.online.shop.system.cart.service.domain;

import com.online.shop.system.cart.service.domain.dto.message.CartOrderResponse;
import com.online.shop.system.cart.service.domain.dto.message.CartRequest;
import com.online.shop.system.cart.service.domain.ports.input.message.listener.OrderMessageListener;
import com.online.shop.system.cart.service.domain.ports.input.service.CartApplicationService;
import com.online.shop.system.cart.service.domain.ports.output.message.publisher.OrderMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderMessageListenerImpl implements OrderMessageListener {

    private final CartApplicationService cartApplicationService;
    private final OrderMessagePublisher orderMessagePublisher;

    @Override
    public void cartRequest(CartRequest cartRequest) {
        CartOrderResponse cartOrderResponse = cartApplicationService.requestCart(cartRequest);
        orderMessagePublisher.publish(cartOrderResponse);
        cartApplicationService.emptyCart(cartRequest.getCartID());
    }
}
