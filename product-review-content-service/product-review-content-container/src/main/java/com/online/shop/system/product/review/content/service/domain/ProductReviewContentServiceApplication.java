package com.online.shop.system.product.review.content.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.online.shop.system.product.review.content.service")
@EntityScan(basePackages = "com.online.shop.system.product.review.content.service")
@SpringBootApplication(scanBasePackages = "com.online.shop.system")
public class ProductReviewContentServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductReviewContentServiceApplication.class, args);
    }
}
