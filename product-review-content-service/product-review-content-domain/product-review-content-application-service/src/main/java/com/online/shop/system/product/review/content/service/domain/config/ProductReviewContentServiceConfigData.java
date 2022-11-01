package com.online.shop.system.product.review.content.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "product-review-content-service")
public class ProductReviewContentServiceConfigData {
    private String productReviewContentTopicName;
}
