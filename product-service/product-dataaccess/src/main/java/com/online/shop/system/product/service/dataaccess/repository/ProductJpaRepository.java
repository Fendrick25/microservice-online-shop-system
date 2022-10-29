package com.online.shop.system.product.service.dataaccess.repository;

import com.online.shop.system.product.service.dataaccess.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {

    Page<ProductEntity> findByCategoryId(UUID categoryID, Pageable pageable);
    Page<ProductEntity> findByNameContaining(String productName, Pageable pageable);
}
