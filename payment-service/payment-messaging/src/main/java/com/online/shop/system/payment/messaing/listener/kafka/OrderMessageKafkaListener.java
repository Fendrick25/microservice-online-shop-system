package com.online.shop.system.payment.messaing.listener.kafka;

import com.online.shop.system.kafka.avro.model.PaymentRequestAvroModel;
import com.online.shop.system.kafka.consumer.KafkaConsumer;
import com.online.shop.system.payment.messaing.mapper.PaymentMessagingDataMapper;
import com.online.shop.system.payment.service.domain.dto.message.CancelPayment;
import com.online.shop.system.payment.service.domain.dto.message.PaymentRequest;
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

        messages.forEach(paymentRequestAvroModel -> orderMessageListener.createPayment(paymentMessagingDataMapper.paymentRequestAvroModelToPaymentRequest(paymentRequestAvroModel)));
    }
}
