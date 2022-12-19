package com.online.shop.system.payment.service.dataaccess.adapter;

import com.online.shop.system.payment.service.dataaccess.mapper.PaymentDataAccessMapper;
import com.online.shop.system.payment.service.dataaccess.repository.UserJpaRepository;
import com.online.shop.system.payment.service.domain.entity.User;
import com.online.shop.system.payment.service.domain.ports.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final PaymentDataAccessMapper paymentDataAccessMapper;
    private final UserJpaRepository userJpaRepository;

    @Override
    public void createUser(User user) {
        userJpaRepository.save(paymentDataAccessMapper.userToUserEntity(user));
    }
}
