package com.online.shop.system.payment.service.domain.ports.input.message.listener;

import com.online.shop.system.payment.service.domain.entity.User;

public interface UserMessageListener {
    void userCreated(User user);
}
