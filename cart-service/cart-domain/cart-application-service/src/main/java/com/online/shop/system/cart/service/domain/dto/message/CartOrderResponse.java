package com.online.shop.system.cart.service.domain.dto.message;

import com.online.shop.system.cart.service.domain.dto.create.response.GetCartResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CartOrderResponse {

    private final GetCartResponse getCartResponse;
    private final UUID orderID;
}
