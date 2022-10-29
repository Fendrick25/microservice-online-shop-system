package com.online.shop.system.product.service.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ProductReview {

    private final Integer productReviewID;
    private final UUID productID;
    private final UUID userID;
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
