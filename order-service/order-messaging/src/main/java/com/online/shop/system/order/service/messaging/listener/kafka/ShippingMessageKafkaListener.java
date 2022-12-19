package com.online.shop.system.order.service.messaging.listener.kafka;

import com.online.shop.system.kafka.avro.model.OrderStatus;
import com.online.shop.system.kafka.avro.model.ShippingResponseAvroModel;
import com.online.shop.system.kafka.consumer.KafkaConsumer;
import com.online.shop.system.order.service.domain.ports.input.message.listener.ShippingMessageListener;
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
public class ShippingMessageKafkaListener implements KafkaConsumer<ShippingResponseAvroModel> {

    private final ShippingMessageListener shippingMessageListener;
    private final OrderMessagingDataMapper orderMessagingDataMapper;

    @Override
    @KafkaListener(groupId = "kafka-consumer-config.shipping-consumer-group-id", topics = "order-service.shipping-response-topic-name")
    public void receive(@Payload List<ShippingResponseAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info("{} number of shipping responses received with keys: {}, partitions: {} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(shippingResponseAvroModel -> {
                if(shippingResponseAvroModel.getShippingStatus().name().equals(OrderStatus.SHIPPED.name()))
                    shippingMessageListener.orderShipped(orderMessagingDataMapper.shippingResponseAvroModelToUpdateOrderDetail(shippingResponseAvroModel));
                if(shippingResponseAvroModel.getShippingStatus().name().equals(OrderStatus.ARRIVED.name()))
                    shippingMessageListener.orderArrived(orderMessagingDataMapper.shippingResponseAvroModelToUpdateOrderDetail(shippingResponseAvroModel));
                if(shippingResponseAvroModel.getShippingStatus().name().equals(OrderStatus.CONFIRMED.name()))
                    shippingMessageListener.orderConfirmed(orderMessagingDataMapper.shippingResponseAvroModelToUpdateOrderDetail(shippingResponseAvroModel));
        });
    }
}
