package com.online.shop.system.order.service.messaging.listener.kafka;

import com.online.shop.system.kafka.avro.model.CartOrderResponseAvroModel;
import com.online.shop.system.kafka.consumer.KafkaConsumer;
import com.online.shop.system.order.service.domain.ports.input.message.listener.CartMessageListener;
import com.online.shop.system.order.service.messaging.mapper.OrderMessagingDataMapper;
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
public class CartMessageKafkaListener implements KafkaConsumer<CartOrderResponseAvroModel> {

    private final CartMessageListener cartMessageListener;
    private final OrderMessagingDataMapper orderMessagingDataMapper;

    @Override
    @KafkaListener(id = "${kafka-consumer-config.cart-consumer-group-id}", topics = "${order-service.cart-response-topic-name}")
    public void receive(@Payload List<CartOrderResponseAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info("{} number of cart responses received with keys: {}, partitions: {} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(cartOrderResponseAvroModel -> cartMessageListener.createOrder(orderMessagingDataMapper.cartOrderResponseAvroModelToCartOrderResponse(cartOrderResponseAvroModel)));
    }
}
