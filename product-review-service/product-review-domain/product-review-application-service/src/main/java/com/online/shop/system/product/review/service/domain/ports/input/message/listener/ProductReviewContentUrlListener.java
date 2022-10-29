package com.online.shop.system.product.review.service.domain.ports.input.message.listener;

import com.online.shop.system.product.review.service.domain.dto.message.ProductReviewContentUrl;

public interface ProductReviewContentUrlListener {

    void productReviewContentUrlCreated(ProductReviewContentUrl productReviewContentUrl);
}
