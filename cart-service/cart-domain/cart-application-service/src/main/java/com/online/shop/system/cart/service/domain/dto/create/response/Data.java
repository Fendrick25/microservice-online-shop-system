package com.online.shop.system.cart.service.domain.dto.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Data<T>{
    private final T data;
}
