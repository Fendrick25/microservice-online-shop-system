package com.online.shop.system.product.service.domain.dto.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class ProductOverview {
    @NotNull
    private final UUID productID;
    @NotNull
    private final String name;
    @NotNull
    private final BigDecimal price;
    @NotNull
    private final int quantity;
    @NotNull
    private final int soldAmount;
    @NotNull
    private final Double rating;
    @NotNull
    private final String imageUrl;
}
