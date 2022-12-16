package com.online.shop.system.payment.service.domain.dto.request.response;

import com.online.shop.system.payment.service.domain.valueobject.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class PayOrderResponse extends PaymentResponse{

    @Builder
    public PayOrderResponse(@NotNull UUID paymentID, @NotNull UUID orderID, @NotNull PaymentStatus paymentStatus) {
        super(paymentID, orderID, paymentStatus);
    }
}
