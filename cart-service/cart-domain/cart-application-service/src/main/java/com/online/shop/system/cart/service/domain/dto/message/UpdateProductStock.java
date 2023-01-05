package com.online.shop.system.cart.service.domain.dto.message;

import com.online.shop.system.cart.service.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class UpdateProductStock {

    @NotNull
    private List<Product> products;
    @NotNull
    private OrderStatus orderStatus;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Product{
        @NotNull
        private UUID productID;
        @NotNull
        private int quantity;
    }



}
