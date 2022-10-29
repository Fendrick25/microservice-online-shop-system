package com.online.shop.system.product.service.messaging.mapper;

import com.online.shop.system.kafka.avro.model.ProductRatingAvroModel;
import com.online.shop.system.product.service.domain.dto.message.ProductRating;
import org.springframework.stereotype.Component;

@Component
public class ProductMessagingDataMapper {
    public ProductRating productRatingAvroModelToProductRating(ProductRatingAvroModel productRatingAvroModel) {
        return ProductRating.builder()
                .productID(productRatingAvroModel.getProductID())
                .rating(productRatingAvroModel.getRating())
                .build();
    }
}
