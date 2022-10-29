package com.online.shop.system.product.service.domain.dto.create;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class CreateCategory {

    @NotNull
    private final String name;
}
