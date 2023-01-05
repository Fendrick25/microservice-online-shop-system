package com.online.shop.system.payment.service.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class PayOrder {
    @NotNull
    private final UUID paymentID;
    @NotNull
    private final UUID userID;
    @NotNull
    private final UUID orderID;
    @NotNull
    private final BigDecimal price;
}
