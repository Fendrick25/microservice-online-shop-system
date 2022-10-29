package com.online.shop.system.product.review.service.domain.dto.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class GetProductReviewResponse {
    private final Integer productReviewID;
    private final String productID;
    private final String userID;
    private final String username;
    private final int rating;
    private String description;
    private List<String> imageUrls;
    private List<String> videoUrls;
    private ZonedDateTime createdAt;
}
