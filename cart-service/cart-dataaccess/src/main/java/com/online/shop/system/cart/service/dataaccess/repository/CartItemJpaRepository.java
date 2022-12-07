package com.online.shop.system.cart.service.dataaccess.repository;

import com.online.shop.system.cart.service.dataaccess.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartItemJpaRepository extends JpaRepository<CartItemEntity, Integer> {
    void deleteAllByCartId(UUID cartID);
}
