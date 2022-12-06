package com.online.shop.system.cart.service.domain;

import com.online.shop.system.cart.service.domain.dto.create.response.GetCartResponse;
import com.online.shop.system.cart.service.domain.dto.message.CartRequest;
import com.online.shop.system.cart.service.domain.mapper.CartDataMapper;
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
    private final CartDataMapper cartDataMapper;

    @Override
    public void cartRequest(CartRequest cartRequest) {
        GetCartResponse getCartResponse =  cartApplicationService.getCart(cartRequest.getUserID());
        cartApplicationService.emptyCart(cartRequest.getCartID());
        orderMessagePublisher.publish(cartDataMapper.getCartResponseToCart(getCartResponse));
    }
}
