package com.online.shop.system.order.service.domain.dto.create;

import com.online.shop.system.order.service.domain.valueobject.OrderAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrder {
    @NotNull
    private final UUID cartID;
    @NotNull
    private final UUID userID;
    @NotNull
    private final OrderAddress orderAddress;
}
