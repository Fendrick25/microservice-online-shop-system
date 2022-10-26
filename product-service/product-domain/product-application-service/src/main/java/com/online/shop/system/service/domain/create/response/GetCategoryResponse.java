package com.online.shop.system.service.domain.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class GetCategoryResponse {
    private final UUID categoryID;
    private final String name;
}
