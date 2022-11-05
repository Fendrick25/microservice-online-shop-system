package com.online.shop.system.user.service.domain.mapper;

import com.online.shop.system.user.service.domain.dto.create.CreateAddress;
import com.online.shop.system.user.service.domain.dto.create.CreateUser;
import com.online.shop.system.user.service.domain.dto.create.UpdateAddress;
import com.online.shop.system.user.service.domain.dto.create.response.UserAddressResponse;
import com.online.shop.system.user.service.domain.dto.create.response.UserIDResponse;
import com.online.shop.system.user.service.domain.dto.message.UserCreatedEvent;
import com.online.shop.system.user.service.domain.entity.User;
import com.online.shop.system.user.service.domain.valueobject.Address;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserDataMapper {
    public User createUserToUser(CreateUser createUser){
        return User.builder()
                .username(createUser.getUsername())
                .name(createUser.getName())
                .email(createUser.getEmail())
                .password(createUser.getPassword())
                .build();
    }

    public UserIDResponse userToUserIDResponse(User user){
        return UserIDResponse.builder()
                .userID(user.getId())
                .build();
    }

    public Address createAddressToAddress(CreateAddress createAddress){
        return Address.builder()
                .street(createAddress.getStreet())
                .city(createAddress.getCity())
                .postalCode(createAddress.getPostalCode())
                .build();
    }

    public UserAddressResponse addressToUserAddressResponse(UUID userID, Address address){
        return UserAddressResponse.builder()
                .userID(userID)
                .addressID(address.getId())
                .build();
    }

    public Address updateAddressToAddress(UpdateAddress updateAddress){
        return Address.builder()
                .id(updateAddress.getAddressID())
                .street(updateAddress.getStreet())
                .postalCode(updateAddress.getPostalCode())
                .city(updateAddress.getCity())
                .build();
    }

    public UserCreatedEvent userToUserCreatedEvent(User user){
        return UserCreatedEvent.builder()
                .userID(user.getId())
                .build();
    }

}
