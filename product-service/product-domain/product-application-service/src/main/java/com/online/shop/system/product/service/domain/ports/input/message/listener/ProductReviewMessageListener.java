package com.online.shop.system.product.service.domain.ports.input.message.listener;

import com.online.shop.system.product.service.domain.dto.message.ProductRating;

public interface ProductReviewMessageListener {
    void productReviewCreated(ProductRating productRating);
}
