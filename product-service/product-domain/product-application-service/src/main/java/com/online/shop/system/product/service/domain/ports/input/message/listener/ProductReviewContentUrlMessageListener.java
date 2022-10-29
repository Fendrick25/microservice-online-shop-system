package com.online.shop.system.product.service.domain.ports.input.message.listener;

import com.online.shop.system.product.service.domain.dto.message.ProductReviewContentUrl;

public interface ProductReviewContentUrlMessageListener {

    void productReviewContentUrlCreated(ProductReviewContentUrl productReviewContentUrl);
}
