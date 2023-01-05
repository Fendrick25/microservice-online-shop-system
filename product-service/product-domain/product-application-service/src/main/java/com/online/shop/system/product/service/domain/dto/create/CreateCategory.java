package com.online.shop.system.product.service.domain.dto.create;

import lombok.*;

import jakarta.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategory {

    @NotNull
    private String name;
}
