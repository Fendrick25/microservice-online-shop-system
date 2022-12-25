package com.online.shop.system.product.service.domain.dto.message;

import com.online.shop.system.product.service.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class UpdateProductStock {

    @NotNull
    private final List<Product> products;

    @NotNull
    private final OrderStatus orderStatus;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Product{
        @NotNull
        private final UUID productID;
        @NotNull
        private final int quantity;
    }

}
