package com.online.shop.system.user.service.domain;

import com.online.shop.system.user.service.domain.entity.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDomainServiceImpl implements UserDomainService{
    @Override
    public User validateUser(User user) {
        user.initializeUser();
        log.info("User with id: {} initialized", user.getId());
        return user;
    }
}
