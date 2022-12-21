package com.online.shop.system.payment.messaging.listener.kafka;

import com.online.shop.system.kafka.avro.model.PaymentRequestAvroModel;
import com.online.shop.system.kafka.avro.model.PaymentStatus;
import com.online.shop.system.kafka.consumer.KafkaConsumer;
import com.online.shop.system.payment.messaging.mapper.PaymentMessagingDataMapper;
import com.online.shop.system.payment.service.domain.ports.input.message.listener.OrderMessageListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderMessageKafkaListener implements KafkaConsumer<PaymentRequestAvroModel> {

    private final OrderMessageListener orderMessageListener;
    private final PaymentMessagingDataMapper paymentMessagingDataMapper;

    @Override
    @KafkaListener(id = "${kafka-consumer-config.payment-consumer-group-id}", topics = "${payment-service.payment-request-topic-name}")
    public void receive(@Payload List<PaymentRequestAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info("{} number of payment request received with keys: {}, partitions: {} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(paymentRequestAvroModel -> {
                    if(paymentRequestAvroModel.getPaymentStatus().equals(PaymentStatus.WAITING_FOR_PAYMENT))
                        orderMessageListener.createPayment(paymentMessagingDataMapper.paymentRequestAvroModelToPaymentRequest(paymentRequestAvroModel));
                    if(paymentRequestAvroModel.getPaymentStatus().equals(PaymentStatus.CANCELLED))
                        orderMessageListener.cancelPayment(paymentMessagingDataMapper.paymentRequestAvroModelToCancelPayment(paymentRequestAvroModel));
                });

    }
}
