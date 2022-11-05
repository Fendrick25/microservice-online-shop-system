package com.online.shop.system.cart.service.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CheckProductStockResponse {
    private final UUID productID;
    private final String message;
}
