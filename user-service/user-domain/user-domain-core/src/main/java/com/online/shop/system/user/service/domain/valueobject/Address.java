package com.online.shop.system.user.service.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class Address {
    private final Integer id;
    private final String street;
    private final String postalCode;
    private final String city;
}
