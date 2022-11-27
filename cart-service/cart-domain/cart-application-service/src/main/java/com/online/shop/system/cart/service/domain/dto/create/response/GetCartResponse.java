package com.online.shop.system.cart.service.domain.dto.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class GetCartResponse {
    private final UUID cartID;
    private final UUID userID;
    private final List<Items> items;
    private final BigDecimal totalPrice;
    private List<String> messages;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Items{
        private final Integer cartItemID;
        private final Product product;
        private final int quantity;
        private final BigDecimal subTotal;

        @Getter
        @Builder
        @AllArgsConstructor
        public static class Product{
            private final UUID productID;
            private final String name;
            private final String description;
            private final BigDecimal price;
            private final String imageUrl;
        }

    }
}
