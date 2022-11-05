package com.online.shop.system.cart.service.dataaccess.repository;

import com.online.shop.system.cart.service.dataaccess.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartJpaRepository extends JpaRepository<CartEntity, UUID> {

    CartEntity findByUserID(UUID userID);
}
