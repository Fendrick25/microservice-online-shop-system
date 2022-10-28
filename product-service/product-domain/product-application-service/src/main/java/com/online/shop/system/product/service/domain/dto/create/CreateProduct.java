package com.online.shop.system.product.service.domain.dto.create;

import com.online.shop.system.product.service.domain.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class CreateProduct {

    @NotNull
    private final String name;
    @NotNull
    private final String description;
    @NotNull
    private final BigDecimal price;
    @NotNull
    private final int quantity;
    @NotNull
    private final Category category;
}
