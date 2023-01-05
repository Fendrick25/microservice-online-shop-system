package com.online.shop.system.product.service.domain.dto.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ProductReviewResponse {
    @NotNull
    private final Integer productReviewID;
    @NotNull
    private final UUID productID;
}
