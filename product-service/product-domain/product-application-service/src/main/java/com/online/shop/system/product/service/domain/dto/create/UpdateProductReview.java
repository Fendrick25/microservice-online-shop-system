package com.online.shop.system.product.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class UpdateProductReview {
    private final Integer productReviewID;
    private final UUID productID;
    private String description;
}
