package com.online.shop.system.cart.service.domain.dto.create;

import lombok.Builder;
import lombok.Getter;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class AddCartItem extends CartItemDTO {
    @NotNull
    private final UUID cartID;

    @Builder
    public AddCartItem(UUID cartID, @NotNull UUID productID, @NotNull int quantity) {
        super(productID, quantity);
        this.cartID = cartID;
    }
}
