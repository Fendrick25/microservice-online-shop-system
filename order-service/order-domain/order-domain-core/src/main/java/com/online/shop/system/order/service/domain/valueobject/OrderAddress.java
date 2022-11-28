package com.online.shop.system.order.service.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderAddress {
    private final String street;
    private final String postalCode;
    private final String city;
}
