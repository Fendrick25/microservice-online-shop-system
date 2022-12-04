package com.online.shop.system.cart.service.domain.dto.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class GetCartResponse {
    @NotNull
    private final UUID cartID;
    @NotNull
    private final UUID userID;
    @NotNull
    private final List<Item> items;
    @NotNull
    private final BigDecimal totalPrice;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Item {
        @NotNull
        private final Integer cartItemID;
        @NotNull
        private final Product product;
        @NotNull
        private final int quantity;
        @NotNull
        private final BigDecimal subTotal;

        @Getter
        @Builder
        @AllArgsConstructor
        public static class Product{
            @NotNull
            private final UUID productID;
            @NotNull
            private final String name;
            @NotNull
            private final String description;
            @NotNull
            private final BigDecimal price;
            @NotNull
            private final String imageUrl;
        }

    }
}
