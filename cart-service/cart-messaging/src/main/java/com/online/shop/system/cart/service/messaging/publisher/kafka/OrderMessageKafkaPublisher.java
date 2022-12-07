package com.online.shop.system.cart.service.messaging.publisher.kafka;

import com.online.shop.system.cart.service.domain.config.CartServiceConfigData;
import com.online.shop.system.cart.service.domain.dto.message.CartOrderResponse;
import com.online.shop.system.cart.service.domain.ports.output.message.publisher.OrderMessagePublisher;
import com.online.shop.system.cart.service.messaging.mapper.CartMessagingDataMapper;
import com.online.shop.system.kafka.avro.model.CartOrderResponseAvroModel;
import com.online.shop.system.kafka.producer.KafkaMessageHelper;
import com.online.shop.system.kafka.producer.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderMessageKafkaPublisher implements OrderMessagePublisher {

    private final KafkaProducer<String, CartOrderResponseAvroModel> kafkaProducer;
    private final CartServiceConfigData cartServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;
    private final CartMessagingDataMapper cartMessagingDataMapper;

    @Override
    public void publish(CartOrderResponse cartOrderResponse) {

        String id = UUID.randomUUID().toString();
        CartOrderResponseAvroModel cartOrderResponseAvroModel = cartMessagingDataMapper.cartOrderResponseToCartOrderResponseAvroModel(cartOrderResponse);

        try{
            kafkaProducer.send(cartServiceConfigData.getCartResponseTopicName(),
                    id,
                    cartOrderResponseAvroModel,
                    kafkaMessageHelper.getKafkaCallback(cartServiceConfigData.getCartResponseTopicName(),
                            cartOrderResponseAvroModel));

            log.info("CartOrderResponseAvroModel sent to kafka for order id: {} and id: {}", cartOrderResponse.getOrderID(), id);
        }catch (Exception e){
            log.error("Error while sending CartOrderResponseAvroModel to Kafka with order id: {} and id: {}, error; {}", cartOrderResponse.getOrderID(), id, e.getMessage());
        }

    }
}
