package com.online.shop.system.product.review.content.service.dataaccess.mapper;

import com.online.shop.system.product.review.content.service.dataaccess.entity.ProductReviewContentEntity;
import com.online.shop.system.product.review.content.service.domain.entity.ProductReviewContent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductReviewContentDataAccessMapper {

    public ProductReviewContentEntity productReviewContentToProductReviewContentEntity(ProductReviewContent productReviewContent){
        return ProductReviewContentEntity.builder()
                .productReviewID(productReviewContent.getProductReviewID())
                .productID(productReviewContent.getProductID())
                .contents(productReviewContent.getFile())
                .createdAt(LocalDateTime.from(productReviewContent.getCreatedAt()))
                .build();
    }

}
