package com.online.shop.system.service.domain.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CreateCategory {
    private final Integer categoryID;
    private final String name;
}
