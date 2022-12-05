package com.online.shop.system.order.service.dataaccess.adapter;

import com.online.shop.system.order.service.dataaccess.mapper.OrderDataAccessMapper;
import com.online.shop.system.order.service.dataaccess.repository.UserJpaRepository;
import com.online.shop.system.order.service.domain.entity.User;
import com.online.shop.system.order.service.domain.ports.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final OrderDataAccessMapper orderDataAccessMapper;
    @Override
    public void createUser(User user) {
        userJpaRepository.save(orderDataAccessMapper.userToUserEntity(user));
    }
}
