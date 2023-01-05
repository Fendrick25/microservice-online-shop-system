package com.online.shop.system.cart.service.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CheckProductStock {
    @NotNull
    private final UUID productID;
    @NotNull
    private final int quantity;
}
