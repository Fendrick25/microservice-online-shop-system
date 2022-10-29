package com.online.shop.system.product.review.service.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ProductReview {

    private final Integer productReviewID;
    private final String productID;
    private final String userID;
    private final String username;
    private final int rating;
    private String description;
    private List<String> imageUrls;
    private List<String> videoUrls;
    private ZonedDateTime createdAt;

    public void initializeProductReview(){
        createdAt = ZonedDateTime.now(ZoneId.of("UTC"));
    }
}
