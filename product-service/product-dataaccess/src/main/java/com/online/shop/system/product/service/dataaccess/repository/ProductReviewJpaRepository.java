package com.online.shop.system.product.service.dataaccess.repository;

import com.online.shop.system.product.service.dataaccess.entity.ProductReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductReviewJpaRepository extends JpaRepository<ProductReviewEntity, Integer> {
    Page<ProductReviewEntity> findByProductId(UUID productID, Pageable paging);

    Page<ProductReviewEntity> findByProductIdAndRating(UUID productID, int rating, Pageable paging);
    ProductReviewEntity findByProductId(UUID productID);
}
