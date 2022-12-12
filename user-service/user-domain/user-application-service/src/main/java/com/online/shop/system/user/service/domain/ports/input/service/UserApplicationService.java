package com.online.shop.system.user.service.domain.ports.input.service;

import com.online.shop.system.user.service.domain.dto.create.CreateAddress;
import com.online.shop.system.user.service.domain.dto.create.CreateUser;
import com.online.shop.system.user.service.domain.dto.create.UpdateAddress;
import com.online.shop.system.user.service.domain.dto.create.response.AddressIDResponse;
import com.online.shop.system.user.service.domain.dto.create.response.UserAddressResponse;
import com.online.shop.system.user.service.domain.dto.create.response.UserIDResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;


public interface UserApplicationService {
    UserIDResponse createUser(@Valid CreateUser createUser);
    UserAddressResponse addAddress(@Valid CreateAddress createAddress);
    UserAddressResponse updateAddress(@Valid UpdateAddress updateAddress);
    AddressIDResponse deleteAddress(Integer addressID);
}
