package com.online.shop.system.order.service.messaging.publisher.kafka;

import com.online.shop.system.kafka.avro.model.CartRequestAvroModel;
import com.online.shop.system.kafka.producer.KafkaMessageHelper;
import com.online.shop.system.kafka.producer.service.KafkaProducer;
import com.online.shop.system.order.service.domain.config.OrderServiceConfigData;
import com.online.shop.system.order.service.domain.event.OrderRequestEvent;
import com.online.shop.system.order.service.domain.ports.output.message.publisher.CartMessagePublisher;
import com.online.shop.system.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class CartMessageKafkaPublisher implements CartMessagePublisher {
    private final KafkaProducer<String, CartRequestAvroModel> kafkaProducer;
    private final OrderServiceConfigData orderServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;
    private final OrderMessagingDataMapper orderMessagingDataMapper;

    @Override
    public void publish(OrderRequestEvent orderRequestEvent) {
        String id = UUID.randomUUID().toString();
        CartRequestAvroModel cartRequestAvroModel = orderMessagingDataMapper.orderRequestEventToCartRequestAvroModel(orderRequestEvent);

        try{
            kafkaProducer.send(orderServiceConfigData.getCartRequestTopicName(),
                    id,
                    cartRequestAvroModel,
                    kafkaMessageHelper.getKafkaCallback(orderServiceConfigData.getCartRequestTopicName(),
                            cartRequestAvroModel));

            log.info("CartRequestAvroModel sent to kafka for order id: {} and id: {}", cartRequestAvroModel.getOrderID(), id);
        }catch (Exception e){
            log.error("Error while sending CartRequestAvroModel to Kafka with order id: {} and id: {}, error; {}", cartRequestAvroModel.getOrderID(), id, e.getMessage());
        }
    }
}
