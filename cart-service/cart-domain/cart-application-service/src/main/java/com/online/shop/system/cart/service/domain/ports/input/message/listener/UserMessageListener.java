package com.online.shop.system.cart.service.domain.ports.input.message.listener;

import com.online.shop.system.cart.service.domain.dto.message.User;

public interface UserMessageListener {
    void UserCreated(User user);
}
