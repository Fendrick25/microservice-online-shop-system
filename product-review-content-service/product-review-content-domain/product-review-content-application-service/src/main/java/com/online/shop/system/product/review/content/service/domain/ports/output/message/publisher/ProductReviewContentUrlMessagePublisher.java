package com.online.shop.system.product.review.content.service.domain.ports.output.message.publisher;

import com.online.shop.system.product.review.content.service.domain.dto.ProductReviewContentUrl;

public interface ProductReviewContentUrlMessagePublisher {
    void publish(ProductReviewContentUrl productReviewContentUrl);
}
