package com.online.shop.system.order.service.domain.ports.output.repository;

import com.online.shop.system.order.service.domain.entity.User;

public interface UserRepository {
    void createUser(User user);
}
