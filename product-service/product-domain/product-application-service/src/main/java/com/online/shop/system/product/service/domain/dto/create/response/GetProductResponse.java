package com.online.shop.system.product.service.domain.dto.create.response;

import com.online.shop.system.product.service.domain.entity.Category;
import com.online.shop.system.product.service.domain.valueobject.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class GetProductResponse {
    @NotNull
    private UUID productID;
    @NotNull
    private final String name;
    @NotNull
    private final String description;
    @NotNull
    private final BigDecimal price;
    @NotNull
    private final int quantity;
    @NotNull
    private ProductStatus productStatus;
    @NotNull
    private final double rating;
    @NotNull
    private final int soldAmount;
    @NotNull
    private String imageUrl;
    @NotNull
    private final Category category;
}
