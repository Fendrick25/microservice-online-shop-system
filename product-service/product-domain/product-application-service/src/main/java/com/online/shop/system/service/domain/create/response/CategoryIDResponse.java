package com.online.shop.system.service.domain.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CategoryIDResponse {
    private final UUID categoryID;
}
