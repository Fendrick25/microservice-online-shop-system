package com.online.shop.system.order.service.domain.dto.create.response;

import com.online.shop.system.order.service.domain.valueobject.OrderAddress;
import com.online.shop.system.order.service.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class GetOrderResponse {
    private final UUID orderID;
    private final UUID userID;
    private final List<OrderItem> items;
    private final BigDecimal totalPrice;
    private final OrderAddress orderAddress;
    private final List<OrderDetail> details;


    @Getter
    @Builder
    @AllArgsConstructor
    public static class OrderItem{
        private final Integer orderItemID;
        private final Product product;
        private final int quantity;
        private BigDecimal subTotal;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Product{
        private final UUID id;
        private final String name;
        private final String description;
        private final BigDecimal price;
        private final String imageUrl;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class OrderDetail{
        private final String id;
        private final OrderStatus orderStatus;
        private ZonedDateTime createdAt;
        private String message;
    }
}
