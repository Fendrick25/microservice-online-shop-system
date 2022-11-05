package com.online.shop.system.product.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class UpdateProductReview {
    @NotNull
    private final Integer productReviewID;
    @NotNull
    private final UUID productID;
    @NotNull
    private String description;
}
