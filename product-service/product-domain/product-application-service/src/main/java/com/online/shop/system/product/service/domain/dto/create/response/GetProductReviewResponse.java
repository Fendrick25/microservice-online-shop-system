package com.online.shop.system.product.service.domain.dto.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class GetProductReviewResponse {
    private final Integer productReviewID;
    private final UUID productID;
    private final UUID userID;
    private final String username;
    private final int rating;
    private String description;
    private String imageUrl;
    private String videoUrl;
    private ZonedDateTime createdAt;
}
