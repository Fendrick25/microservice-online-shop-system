package com.online.shop.system.payment.messaing.mapper;

import com.online.shop.system.kafka.avro.model.PaymentRequestAvroModel;
import com.online.shop.system.kafka.avro.model.PaymentResponseAvroModel;
import com.online.shop.system.kafka.avro.model.UserAvroModel;
import com.online.shop.system.payment.service.domain.dto.message.PaymentRequest;
import com.online.shop.system.payment.service.domain.entity.User;
import com.online.shop.system.payment.service.domain.event.PaymentCancelledEvent;
import com.online.shop.system.payment.service.domain.event.PaymentCreatedEvent;
import com.online.shop.system.payment.service.domain.event.PaymentExpiredEvent;
import com.online.shop.system.payment.service.domain.event.PaymentPaidEvent;
import com.online.shop.system.payment.service.domain.valueobject.PaymentStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentMessagingDataMapper {

    public PaymentRequest paymentRequestAvroModelToPaymentRequest(PaymentRequestAvroModel paymentRequestAvroModel) {
        return PaymentRequest.builder()
                .orderID(UUID.fromString(paymentRequestAvroModel.getOrderID()))
                .userID(UUID.fromString(paymentRequestAvroModel.getUserID()))
                .price(paymentRequestAvroModel.getPrice())
                .paymentStatus(PaymentStatus.valueOf(paymentRequestAvroModel.getPaymentStatus().name()))
                .build();
    }

    public User userAvroModelToUser(UserAvroModel userAvroModel){
        return User.builder()
                .id(UUID.fromString(userAvroModel.getUserID()))
                .build();
    }

    public PaymentResponseAvroModel paymentCreatedEventToPaymentResponseAvroModel(PaymentCreatedEvent paymentCreatedEvent) {
        return PaymentResponseAvroModel.newBuilder()
                .setOrderID(paymentCreatedEvent.getOrderID().toString())
                .setUserID(paymentCreatedEvent.getUserID().toString())
                .setPaymentStatus(com.online.shop.system.kafka.avro.model.PaymentStatus.valueOf(paymentCreatedEvent.getPaymentStatus().name()))
                .build();
    }

    public PaymentResponseAvroModel paymentPaidEventToPaymentResponseAvroModel(PaymentPaidEvent paymentPaidEvent) {
        return PaymentResponseAvroModel.newBuilder()
                .setOrderID(paymentPaidEvent.getOrderID().toString())
                .setUserID(paymentPaidEvent.getUserID().toString())
                .setPaymentStatus(com.online.shop.system.kafka.avro.model.PaymentStatus.valueOf(paymentPaidEvent.getPaymentStatus().name()))
                .build();
    }

    public PaymentResponseAvroModel paymentCancelledEventToPaymentResponseAvroModel(PaymentCancelledEvent paymentCancelledEvent) {
        return PaymentResponseAvroModel.newBuilder()
                .setOrderID(paymentCancelledEvent.getOrderID().toString())
                .setUserID(paymentCancelledEvent.getUserID().toString())
                .setPaymentStatus(com.online.shop.system.kafka.avro.model.PaymentStatus.valueOf(paymentCancelledEvent.getPaymentStatus().name()))
                .build();
    }

    public PaymentResponseAvroModel paymentExpiredEventToPaymentResponseAvroModel(PaymentExpiredEvent paymentExpiredEvent) {
        return PaymentResponseAvroModel.newBuilder()
                .setOrderID(paymentExpiredEvent.getOrderID().toString())
                .setUserID(paymentExpiredEvent.getUserID().toString())
                .setPaymentStatus(com.online.shop.system.kafka.avro.model.PaymentStatus.valueOf(paymentExpiredEvent.getPaymentStatus().name()))
                .build();
    }
}
