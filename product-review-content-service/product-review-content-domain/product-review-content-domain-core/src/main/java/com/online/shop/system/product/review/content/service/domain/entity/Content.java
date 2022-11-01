package com.online.shop.system.product.review.content.service.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Content {
    private final String name;
    private final String type;
    private final String size;
    private byte[] file;
}
