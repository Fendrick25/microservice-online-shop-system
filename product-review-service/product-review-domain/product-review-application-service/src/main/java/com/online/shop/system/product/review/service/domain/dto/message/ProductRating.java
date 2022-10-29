package com.online.shop.system.product.review.service.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ProductRating {

    private final UUID productID;
    private final int rating;
}
