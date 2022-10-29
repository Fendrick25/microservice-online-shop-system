package com.online.shop.system.product.review.service.domain.ports.output.message.publisher;

import com.online.shop.system.product.review.service.domain.dto.message.ProductRating;

public interface ProductRatingMessagePublisher {

    void publish(ProductRating productRating);
}
