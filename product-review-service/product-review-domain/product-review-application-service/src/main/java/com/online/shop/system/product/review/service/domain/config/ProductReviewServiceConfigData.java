package com.online.shop.system.product.review.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "product-review-service")
public class ProductReviewServiceConfigData {
    private String productReviewTopicName;
}