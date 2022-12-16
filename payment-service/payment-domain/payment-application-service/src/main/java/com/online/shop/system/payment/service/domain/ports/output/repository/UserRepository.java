package com.online.shop.system.payment.service.domain.ports.output.repository;

import com.online.shop.system.payment.service.domain.entity.User;

public interface UserRepository {

    void createUser(User user);
}
