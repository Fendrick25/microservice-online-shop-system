package com.online.shop.system.user.service.dataaccess.mapper;

import com.online.shop.system.user.service.dataaccess.entity.AddressEntity;
import com.online.shop.system.user.service.dataaccess.entity.UserEntity;
import com.online.shop.system.user.service.domain.entity.User;
import com.online.shop.system.user.service.domain.valueobject.Address;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDataAccessMapper {

    public UserEntity userToUserEntity(User user){
        return UserEntity.builder()
                .userID(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public User userEntityToUser(UserEntity userEntity){
        return User.builder()
                .id(userEntity.getUserID())
                .name(userEntity.getName())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .createdAt(userEntity.getCreatedAt())
                .address(addressEntitiesToAddress(userEntity.getAddress()))
                .build();
    }

    public Address addressEntityToAddress(AddressEntity addressEntity){
        return Address.builder()
                .id(addressEntity.getAddressID())
                .street(addressEntity.getStreet())
                .city(addressEntity.getCity())
                .postalCode(addressEntity.getPostalCode())
                .build();
    }

    public AddressEntity addressToAddressEntity(Address address){
        return AddressEntity.builder()
                .street(address.getStreet())
                .postalCode(address.getPostalCode())
                .city(address.getCity())
                .build();
    }

    public List<Address> addressEntitiesToAddress(List<AddressEntity> addressEntities){
        if(addressEntities !=  null)
            return addressEntities.stream().map(this::addressEntityToAddress).collect(Collectors.toList());
        return null;

    }

}
