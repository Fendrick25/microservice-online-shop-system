package com.online.shop.system.payment.service.domain.dto.request.response;

import com.online.shop.system.payment.service.domain.valueobject.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
public abstract class PaymentResponse{

    @NotNull
    private final UUID paymentID;
    @NotNull
    private final UUID orderID;
    @NotNull
    private final PaymentStatus paymentStatus;
}
