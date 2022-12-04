package com.online.shop.system.order.service.domain;

import com.online.shop.system.order.service.domain.entity.User;
import com.online.shop.system.order.service.domain.ports.input.message.listener.UserMessageListener;
import com.online.shop.system.order.service.domain.ports.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserMessageListenerImpl implements UserMessageListener {

    private final UserRepository userRepository;

    @Override
    public void userCreated(User user) {
        userRepository.createUser(user);
        log.info("User created with id: " + user.getId());
    }
}
