package com.online.shop.system.product.service.dataaccess.repository;

import com.online.shop.system.product.service.dataaccess.entity.ProductRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRatingJpaRepository extends JpaRepository<ProductRatingEntity, Integer> {
}
