package com.online.shop.system.cart.service.domain;

import com.online.shop.system.cart.service.domain.dto.message.User;
import com.online.shop.system.cart.service.domain.ports.input.message.listener.UserMessageListener;
import com.online.shop.system.cart.service.domain.ports.input.service.CartApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMessageListenerImpl implements UserMessageListener {

    private final CartApplicationService cartApplicationService;

    @Override
    public void userCreated(User user) {
        cartApplicationService.createCart(user);
    }
}
