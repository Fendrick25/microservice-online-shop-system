package com.online.shop.system.product.review.content.service.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ProductReviewContent {
    private String id;
    private final Integer productReviewID;
    private final String productID;
    @Setter
    private List<Content> file;
    private ZonedDateTime createdAt;

    public void initializeProductReviewContent(){
        id = UUID.randomUUID().toString();
        createdAt = ZonedDateTime.now(ZoneId.of("UTC"));
    }
}
