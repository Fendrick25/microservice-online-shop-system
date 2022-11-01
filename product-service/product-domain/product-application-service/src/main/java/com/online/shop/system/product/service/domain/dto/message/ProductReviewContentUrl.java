package com.online.shop.system.product.service.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ProductReviewContentUrl {
    private Integer productReviewID;
    private UUID productID;
    private String imageUrl;
    private String videoUrl;
}
