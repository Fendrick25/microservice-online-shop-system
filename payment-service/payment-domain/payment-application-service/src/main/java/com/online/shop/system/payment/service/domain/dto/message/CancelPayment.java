package com.online.shop.system.payment.service.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CancelPayment {

    private UUID orderID;
    private UUID userID;
}
