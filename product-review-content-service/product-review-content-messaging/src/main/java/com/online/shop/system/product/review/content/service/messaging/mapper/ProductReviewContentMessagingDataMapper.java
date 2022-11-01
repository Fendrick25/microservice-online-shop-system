package com.online.shop.system.product.review.content.service.messaging.mapper;

import com.online.shop.system.kafka.avro.model.ProductReviewContentUrlAvroModel;
import com.online.shop.system.product.review.content.service.domain.dto.ProductReviewContentUrl;
import org.springframework.stereotype.Component;

@Component
public class ProductReviewContentMessagingDataMapper {

    public ProductReviewContentUrlAvroModel productReviewContentUrlToProductReviewContentUrlAvroModel(ProductReviewContentUrl productReviewContentUrl){
        return ProductReviewContentUrlAvroModel.newBuilder()
                .setProductReviewID(productReviewContentUrl.getProductReviewID())
                .setProductID(productReviewContentUrl.getProductID().toString())
                .setImageUrl(productReviewContentUrl.getImageUrl())
                .setVideoUrl(productReviewContentUrl.getVideoUrl())
                .build();
    }
}
