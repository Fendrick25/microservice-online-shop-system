package com.online.shop.system.product.service.dataaccess.repository;

import com.online.shop.system.product.service.dataaccess.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {

    Page<ProductEntity> findByCategoryId(UUID categoryID, Pageable pageable);
    Page<ProductEntity> findByNameContaining(String productName, Pageable pageable);
    @Query("SELECT p.quantity from ProductEntity p WHERE p.id = ?1")
    int getProductQuantity(UUID productID);

    List<ProductEntity> findByIdIn(List<UUID> productIDs);
}
