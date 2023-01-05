package com.online.shop.system.product.service.domain.dto.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import jakarta.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class GetProductReviewResponse {
    @NotNull
    private final Integer productReviewID;
    @NotNull
    private final UUID productID;
    @NotNull
    private final UUID userID;
    @NotNull
    private final String username;
    @NotNull
    private final int rating;
    private String description;
    private String imageUrl;
    private String videoUrl;
    @NotNull
    private ZonedDateTime createdAt;
}
