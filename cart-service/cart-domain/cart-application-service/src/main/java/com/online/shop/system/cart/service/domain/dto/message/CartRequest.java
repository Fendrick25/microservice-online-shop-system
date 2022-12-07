package com.online.shop.system.cart.service.domain.dto.message;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CartRequest {
    private UUID orderID;
    private UUID cartID;
    private UUID userID;
}
