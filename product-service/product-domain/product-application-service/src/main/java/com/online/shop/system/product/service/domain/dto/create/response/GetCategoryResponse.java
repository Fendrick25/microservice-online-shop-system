package com.online.shop.system.product.service.domain.dto.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class GetCategoryResponse {
    @NotNull
    private final UUID categoryID;
    @NotNull
    private final String name;
}
