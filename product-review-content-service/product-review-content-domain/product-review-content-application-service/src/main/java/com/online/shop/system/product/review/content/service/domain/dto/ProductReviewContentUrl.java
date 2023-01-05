package com.online.shop.system.product.review.content.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ProductReviewContentUrl {
    @NotNull
    private Integer productReviewID;
    @NotNull
    private UUID productID;
    @NotNull
    private String imageUrl;
    @NotNull
    private String videoUrl;
}
