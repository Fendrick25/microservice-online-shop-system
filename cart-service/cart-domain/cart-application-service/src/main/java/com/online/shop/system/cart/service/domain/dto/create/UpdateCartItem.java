package com.online.shop.system.cart.service.domain.dto.create;

import lombok.Builder;
import lombok.Getter;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Getter

public class UpdateCartItem extends CartItemDTO {
    @NotNull
    private final Integer cartItemID;
    @Builder
    public UpdateCartItem(Integer cartItemID, @NotNull UUID productID, @NotNull int quantity) {
        super(productID, quantity);
        this.cartItemID = cartItemID;
    }
}
