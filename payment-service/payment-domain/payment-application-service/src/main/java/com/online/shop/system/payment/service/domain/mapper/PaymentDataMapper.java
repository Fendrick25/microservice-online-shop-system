package com.online.shop.system.payment.service.domain.mapper;

import com.online.shop.system.payment.service.domain.dto.message.PaymentRequest;
import com.online.shop.system.payment.service.domain.dto.request.response.PayOrderResponse;
import com.online.shop.system.payment.service.domain.entity.Payment;
import com.online.shop.system.payment.service.domain.event.PaymentCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class PaymentDataMapper {

    public Payment paymentRequestToPayment(PaymentRequest paymentRequest){
        return Payment.builder()
                .orderID(paymentRequest.getOrderID())
                .userID(paymentRequest.getUserID())
                .price(paymentRequest.getPrice())
                .paymentStatus(paymentRequest.getPaymentStatus())
                .build();
    }

    public PaymentCreatedEvent paymentToPaymentCreatedEvent(Payment payment){
        return PaymentCreatedEvent.builder()
                .paymentID(payment.getId())
                .orderID(payment.getOrderID())
                .userID(payment.getUserID())
                .paymentStatus(payment.getPaymentStatus())
                .purchasedAt(payment.getPurchaseDate())
                .build();
    }

    public PayOrderResponse paymentToPayOrderResponse(Payment payment){
        return PayOrderResponse.builder()
                .orderID(payment.getOrderID())
                .paymentID(payment.getId())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }
}
