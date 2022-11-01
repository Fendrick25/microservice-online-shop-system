package com.online.shop.system.product.service.messaging.mapper;

import com.online.shop.system.kafka.avro.model.ProductReviewContentUrlAvroModel;
import com.online.shop.system.product.service.domain.dto.message.ProductReviewContentUrl;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductMessagingDataMapper {

    public ProductReviewContentUrl productReviewContentUrlAvroModelToProductReviewContentUrl(ProductReviewContentUrlAvroModel productReviewContentUrlAvroModel) {
        return ProductReviewContentUrl.builder()
                .productReviewID(productReviewContentUrlAvroModel.getProductReviewID())
                .productID(UUID.fromString(productReviewContentUrlAvroModel.getProductID()))
                .imageUrl(productReviewContentUrlAvroModel.getImageUrl())
                .videoUrl(productReviewContentUrlAvroModel.getVideoUrl())
                .build();
    }
}
