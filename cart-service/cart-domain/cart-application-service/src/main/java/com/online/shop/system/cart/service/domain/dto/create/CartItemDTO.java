package com.online.shop.system.cart.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Getter;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CartItemDTO {
    @NotNull
    private final UUID productID;
    @NotNull
    private final int quantity;
}
