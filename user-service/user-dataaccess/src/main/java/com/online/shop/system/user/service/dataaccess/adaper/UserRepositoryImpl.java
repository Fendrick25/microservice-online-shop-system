package com.online.shop.system.user.service.dataaccess.adaper;

import com.online.shop.system.user.service.dataaccess.entity.AddressEntity;
import com.online.shop.system.user.service.dataaccess.entity.UserEntity;
import com.online.shop.system.user.service.dataaccess.exception.ResourceNotFoundException;
import com.online.shop.system.user.service.dataaccess.mapper.UserDataAccessMapper;
import com.online.shop.system.user.service.dataaccess.repository.UserAddressJpaRepository;
import com.online.shop.system.user.service.dataaccess.repository.UserJpaRepository;
import com.online.shop.system.user.service.domain.entity.User;
import com.online.shop.system.user.service.domain.ports.output.repository.UserRepository;
import com.online.shop.system.user.service.domain.valueobject.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserAddressJpaRepository userAddressJpaRepository;
    private final UserDataAccessMapper userDataAccessMapper;


    @Override
    public User createUser(User user) {
         return userDataAccessMapper.userEntityToUser(userJpaRepository.save(userDataAccessMapper.userToUserEntity(user)));
    }

    @Override
    public Address createAddress(UUID userID, Address address) {
        AddressEntity addressEntity = userDataAccessMapper.addressToAddressEntity(address);
        addressEntity.setUser(findUser(userID));
        return userDataAccessMapper.addressEntityToAddress(userAddressJpaRepository.save(addressEntity));
    }

    @Override
    public Address updateAddress(UUID userID, Address address) {
        findUser(userID);
        AddressEntity addressEntity = userAddressJpaRepository.findById(address.getId()).orElseThrow(() -> new ResourceNotFoundException("Address not found!"));
        addressEntity.setStreet(address.getStreet());
        addressEntity.setPostalCode(address.getPostalCode());
        addressEntity.setCity(address.getCity());
        return userDataAccessMapper.addressEntityToAddress(userAddressJpaRepository.save(addressEntity));
    }

    @Override
    public void deleteAddress(Integer addressID) {
        userAddressJpaRepository.deleteById(addressID);
    }

    private UserEntity findUser(UUID userID){
        return userJpaRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }
}
