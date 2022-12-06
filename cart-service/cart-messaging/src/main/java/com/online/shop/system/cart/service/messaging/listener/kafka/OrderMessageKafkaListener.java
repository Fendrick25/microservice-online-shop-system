package com.online.shop.system.cart.service.messaging.listener.kafka;

import com.online.shop.system.cart.service.domain.ports.input.message.listener.OrderMessageListener;
import com.online.shop.system.cart.service.messaging.mapper.CartMessagingDataMapper;
import com.online.shop.system.kafka.avro.model.CartRequestAvroModel;
import com.online.shop.system.kafka.consumer.KafkaConsumer;
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
public class OrderMessageKafkaListener implements KafkaConsumer<CartRequestAvroModel> {

    private final OrderMessageListener orderMessageListener;
    private final CartMessagingDataMapper cartMessagingDataMapper;


    @Override
    @KafkaListener(id = "${kafka-consumer-config.cart-consumer-group-id}", topics = "${cart-service.cart-request-topic-name}")
    public void receive(@Payload List<CartRequestAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info("{} number of cart requests received with keys: {}, partitions: {} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(cartRequestAvroModel ->
                orderMessageListener.cartRequest(cartMessagingDataMapper.cartRequestAvroModelToCartRequest(cartRequestAvroModel)));
    }
}
