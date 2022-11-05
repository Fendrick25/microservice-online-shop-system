package com.online.shop.system.cart.service.domain.dto.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CartIDResponse {
    private final UUID cartID;
}
