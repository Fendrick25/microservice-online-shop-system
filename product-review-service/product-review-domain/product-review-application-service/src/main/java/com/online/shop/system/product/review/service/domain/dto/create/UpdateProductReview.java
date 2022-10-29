package com.online.shop.system.product.review.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UpdateProductReview {
    private final Integer productReviewID;
    private final String productID;
    private String description;
}
