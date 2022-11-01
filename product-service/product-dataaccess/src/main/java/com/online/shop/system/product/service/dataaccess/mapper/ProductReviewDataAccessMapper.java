package com.online.shop.system.product.service.dataaccess.mapper;

import com.online.shop.system.product.service.dataaccess.entity.ProductReviewEntity;
import com.online.shop.system.product.service.domain.entity.ProductReview;
import org.springframework.stereotype.Component;

@Component
public class ProductReviewDataAccessMapper {

    public ProductReview productReviewEntityToProductReview(ProductReviewEntity productReviewEntity){
        return ProductReview.builder()
                .productReviewID(productReviewEntity.getProductReviewID())
                .productID(productReviewEntity.getProduct().getId())
                .description(productReviewEntity.getDescription())
                .rating(productReviewEntity.getRating())
                .userID(productReviewEntity.getUserID())
                .username(productReviewEntity.getUsername())
                .imageUrl(productReviewEntity.getImageUrl())
                .videoUrl(productReviewEntity.getVideoUrl())
                .createdAt(productReviewEntity.getCreatedAt())
                .build();
    }

    public ProductReviewEntity productReviewToProductReviewEntity(ProductReview productReview){
        return ProductReviewEntity.builder()
                .description(productReview.getDescription())
                .rating(productReview.getRating())
                .userID(productReview.getUserID())
                .username(productReview.getUsername())
                .imageUrl(productReview.getImageUrl())
                .videoUrl(productReview.getVideoUrl())
                .createdAt(productReview.getCreatedAt())
                .build();
    }
}
