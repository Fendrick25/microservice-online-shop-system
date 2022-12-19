package com.online.shop.system.payment.service.domain.entity;

import com.online.shop.system.payment.service.domain.exception.PaymentDomainException;
import com.online.shop.system.payment.service.domain.valueobject.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Payment {

    private UUID id;
    private UUID orderID;
    private UUID userID;
    private final BigDecimal price;
    private ZonedDateTime purchaseDate;
    private PaymentStatus paymentStatus;

    public void initializePayment(){
        validatePayment();
        id = UUID.randomUUID();
        purchaseDate = ZonedDateTime.now(ZoneId.of("UTC"));
        paymentStatus = PaymentStatus.WAITING_FOR_PAYMENT;
    }

    public void pay(BigDecimal amount){
        if(!amount.equals(price))
            throw new PaymentDomainException("Amount is not sufficient for payment");
        if(paymentStatus != PaymentStatus.WAITING_FOR_PAYMENT)
            throw new PaymentDomainException("Payment is not in correct state for pay operation");

        paymentStatus = PaymentStatus.PAID;
    }

    public void cancel(){
        if(paymentStatus != PaymentStatus.WAITING_FOR_PAYMENT)
            throw new PaymentDomainException("Payment is not in correct state for cancel operation");

        paymentStatus = PaymentStatus.CANCELLED;
    }

    public void expired(){
        if(paymentStatus != PaymentStatus.WAITING_FOR_PAYMENT)
            throw new PaymentDomainException("Payment is not in correct state for expired operation!");

        paymentStatus = PaymentStatus.EXPIRED;
    }

    private void validatePayment(){
        if(price == null)
            throw new PaymentDomainException("Price can't be null");
        if(price.equals(BigDecimal.ZERO))
            throw new PaymentDomainException("Price can't be zero");
        if(price.compareTo(BigDecimal.ZERO) < 0)
            throw new PaymentDomainException("Price must be greater than zero");
    }
}
