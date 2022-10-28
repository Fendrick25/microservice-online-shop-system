package com.online.shop.system.product.service.dataaccess.repository;

import com.online.shop.system.product.service.dataaccess.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, UUID> {
}
