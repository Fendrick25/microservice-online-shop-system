package com.online.shop.system.user.service.dataaccess.repository;

import com.online.shop.system.user.service.dataaccess.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressJpaRepository extends JpaRepository<AddressEntity, Integer> {
}
