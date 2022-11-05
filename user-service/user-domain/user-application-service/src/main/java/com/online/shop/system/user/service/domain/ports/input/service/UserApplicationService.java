package com.online.shop.system.user.service.domain.ports.input.service;

import com.online.shop.system.user.service.domain.dto.create.CreateAddress;
import com.online.shop.system.user.service.domain.dto.create.CreateUser;
import com.online.shop.system.user.service.domain.dto.create.UpdateAddress;
import com.online.shop.system.user.service.domain.dto.create.response.AddressIDResponse;
import com.online.shop.system.user.service.domain.dto.create.response.UserAddressResponse;
import com.online.shop.system.user.service.domain.dto.create.response.UserIDResponse;
import org.springframework.validation.annotation.Validated;


public interface UserApplicationService {
    UserIDResponse createUser(@Validated CreateUser createUser);
    UserAddressResponse addAddress(@Validated CreateAddress createAddress);
    UserAddressResponse updateAddress(@Validated UpdateAddress updateAddress);
    AddressIDResponse deleteAddress(Integer addressID);
}
