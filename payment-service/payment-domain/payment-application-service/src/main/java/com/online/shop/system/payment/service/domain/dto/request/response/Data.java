package com.online.shop.system.payment.service.domain.dto.request.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Data<T>{
    private final T data;
}
