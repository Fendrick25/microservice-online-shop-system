package com.online.shop.system.product.service.messaging.mapper;

import com.online.shop.system.kafka.avro.model.ProductReviewContentUrlAvroModel;
import com.online.shop.system.product.service.domain.dto.message.ProductReviewContentUrl;
import org.springframework.stereotype.Component;

@Component
public class ProductMessagingDataMapper {

    public ProductReviewContentUrl productReviewContentUrlAvroModelToProductReviewContentUrl(ProductReviewContentUrlAvroModel productReviewContentUrlAvroModel) {
        return ProductReviewContentUrl.builder()
                .productID(productReviewContentUrlAvroModel.getProductID())
                .imagesUrl(productReviewContentUrlAvroModel.getImageUrls())
                .videosUrl(productReviewContentUrlAvroModel.getVideoUrls())
                .build();
    }
}
