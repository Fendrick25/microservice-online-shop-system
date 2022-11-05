package com.online.shop.system.user.service.domain.ports.output.message.publisher;

import com.online.shop.system.user.service.domain.dto.message.UserCreatedEvent;

public interface UserMessagePublisher {
    void send(UserCreatedEvent userCreatedEvent);
}
