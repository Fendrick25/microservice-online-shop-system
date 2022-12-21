package com.online.shop.system.payment.messaging.listener.kafka;

import com.online.shop.system.kafka.avro.model.UserAvroModel;
import com.online.shop.system.kafka.consumer.KafkaConsumer;
import com.online.shop.system.payment.messaging.mapper.PaymentMessagingDataMapper;
import com.online.shop.system.payment.service.domain.ports.input.message.listener.UserMessageListener;
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
public class UserMessageKafkaListener implements KafkaConsumer<UserAvroModel> {
    private final UserMessageListener userMessageListener;
    private final PaymentMessagingDataMapper paymentMessagingDataMapper;

    @Override
    @KafkaListener(id = "${kafka-consumer-config.user-group-id}", topics = "${payment-service.user-topic-name}")
    public void receive(@Payload List<UserAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of user received with keys: {}, partitions: {} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(userAvroModel -> userMessageListener.userCreated(paymentMessagingDataMapper.userAvroModelToUser(userAvroModel)));
    }
}
