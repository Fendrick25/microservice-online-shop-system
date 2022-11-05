package com.online.shop.system.user.service.dataaccess.repository;

import com.online.shop.system.user.service.dataaccess.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
}
