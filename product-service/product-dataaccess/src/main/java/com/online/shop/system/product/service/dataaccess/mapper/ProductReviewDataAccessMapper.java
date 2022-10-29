package com.online.shop.system.product.service.dataaccess.mapper;

import com.online.shop.system.product.service.dataaccess.entity.ProductEntity;
import com.online.shop.system.product.service.dataaccess.entity.ProductReviewEntity;
import com.online.shop.system.product.service.domain.entity.ProductReview;
import org.springframework.stereotype.Component;

@Component
public class ProductReviewDataAccessMapper {

    public ProductReview productReviewEntityToProductReview(ProductReviewEntity productReviewEntity){
        return ProductReview.builder()
                .productReviewID(productReviewEntity.getProductReviewID())
                .productID(productReviewEntity.getProduct().getProductID())
                .description(productReviewEntity.getDescription())
                .rating(productReviewEntity.getRating())
                .userID(productReviewEntity.getUserID())
                .username(productReviewEntity.getUsername())
                .imageUrls(productReviewEntity.getImageUrls())
                .videoUrls(productReviewEntity.getVideoUrls())
                .createdAt(productReviewEntity.getCreatedAt())
                .build();
    }

    public ProductReviewEntity productReviewToProductReviewEntity(ProductReview productReview){
        return ProductReviewEntity.builder()
                .product(ProductEntity.builder()
                        .productID(productReview.getProductID())
                        .build())
                .description(productReview.getDescription())
                .rating(productReview.getRating())
                .userID(productReview.getUserID())
                .username(productReview.getUsername())
                .imageUrls(productReview.getImageUrls())
                .videoUrls(productReview.getVideoUrls())
                .createdAt(productReview.getCreatedAt())
                .build();
    }
}
