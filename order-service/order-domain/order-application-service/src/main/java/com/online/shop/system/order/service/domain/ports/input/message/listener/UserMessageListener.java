package com.online.shop.system.order.service.domain.ports.input.message.listener;

import com.online.shop.system.order.service.domain.entity.User;

public interface UserMessageListener {

    void userCreated(User user);
}
