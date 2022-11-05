package com.online.shop.system.user.service.domain.ports.output.repository;

import com.online.shop.system.user.service.domain.entity.User;
import com.online.shop.system.user.service.domain.valueobject.Address;

import java.util.UUID;

public interface UserRepository {
    User createUser(User user);
    Address createAddress(UUID userID, Address address);
    Address updateAddress(UUID addressID, Address address);
    void deleteAddress(Integer addressID);
}
