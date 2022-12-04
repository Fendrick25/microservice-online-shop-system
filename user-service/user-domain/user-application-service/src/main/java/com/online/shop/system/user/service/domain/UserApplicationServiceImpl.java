package com.online.shop.system.user.service.domain;

import com.online.shop.system.user.service.domain.dto.create.CreateAddress;
import com.online.shop.system.user.service.domain.dto.create.CreateUser;
import com.online.shop.system.user.service.domain.dto.create.UpdateAddress;
import com.online.shop.system.user.service.domain.dto.create.response.AddressIDResponse;
import com.online.shop.system.user.service.domain.dto.create.response.UserAddressResponse;
import com.online.shop.system.user.service.domain.dto.create.response.UserIDResponse;
import com.online.shop.system.user.service.domain.entity.User;
import com.online.shop.system.user.service.domain.mapper.UserDataMapper;
import com.online.shop.system.user.service.domain.ports.input.service.UserApplicationService;
import com.online.shop.system.user.service.domain.ports.output.message.publisher.UserMessagePublisher;
import com.online.shop.system.user.service.domain.ports.output.repository.UserRepository;
import com.online.shop.system.user.service.domain.valueobject.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserDomainService userDomainService;
    private final UserDataMapper userDataMapper;
    private final UserRepository userRepository;
    private final UserMessagePublisher userMessagePublisher;

    @Override
    public UserIDResponse createUser(CreateUser createUser) {
        User user = userRepository.createUser(userDomainService.validateUser(userDataMapper.createUserToUser(createUser)));
        userMessagePublisher.publish(userDataMapper.userToUserCreatedEvent(user));
        return userDataMapper.userToUserIDResponse(user);
    }

    @Override
    public UserAddressResponse addAddress(CreateAddress createAddress) {
        Address address = userRepository.createAddress(createAddress.getUserID(), userDataMapper.createAddressToAddress(createAddress));
        log.info("Address created for user id: {}", createAddress.getUserID());
        return userDataMapper.addressToUserAddressResponse(createAddress.getUserID(), address);
    }

    @Override
    public UserAddressResponse updateAddress(UpdateAddress updateAddress) {
        Address address = userRepository.updateAddress(updateAddress.getUserID(), userDataMapper.updateAddressToAddress(updateAddress));
        log.info("Address updated for user id: {}, address id: {}", updateAddress.getUserID(), address.getId());
        return userDataMapper.addressToUserAddressResponse(updateAddress.getUserID(), address);
    }

    @Override
    public AddressIDResponse deleteAddress(Integer addressID) {
        userRepository.deleteAddress(addressID);
        log.info("Address deleted for address id: {}", addressID);
        return AddressIDResponse.builder()
                .addressID(addressID)
                .build();
    }
}
