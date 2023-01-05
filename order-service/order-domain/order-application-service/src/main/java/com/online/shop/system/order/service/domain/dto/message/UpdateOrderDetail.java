package com.online.shop.system.order.service.domain.dto.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.online.shop.system.order.service.domain.valueobject.OrderStatus;
import lombok.*;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class UpdateOrderDetail {
    @NotNull
    private final UUID orderID;
    @NotNull
    private final UUID userID;
    @NotNull
    private final OrderStatus orderStatus;
    private String message;
}
