package com.online.shop.system.product.review.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class CreateProductReview {
    @NotNull
    private final String productID;
    @NotNull
    private final String userID;
    @NotNull
    private final String username;
    @NotNull
    private final int rating;
    private String description;
}
