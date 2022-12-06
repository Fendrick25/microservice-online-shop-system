package com.online.shop.system.cart.service.messaging.publisher.kafka;

import com.online.shop.system.cart.service.domain.config.CartServiceConfigData;
import com.online.shop.system.cart.service.domain.entity.Cart;
import com.online.shop.system.cart.service.domain.ports.output.message.publisher.OrderMessagePublisher;
import com.online.shop.system.cart.service.messaging.mapper.CartMessagingDataMapper;
import com.online.shop.system.kafka.avro.model.OrderAvroModel;
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

    private final KafkaProducer<String, OrderAvroModel> kafkaProducer;
    private final CartServiceConfigData cartServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;
    private final CartMessagingDataMapper cartMessagingDataMapper;

    @Override
    public void publish(Cart cart) {

        String id = UUID.randomUUID().toString();
        OrderAvroModel orderAvroModel = cartMessagingDataMapper.cartToOrderAvroModel(cart);

        try{
            kafkaProducer.send(cartServiceConfigData.getCartResponseTopicName(),
                    id,
                    orderAvroModel,
                    kafkaMessageHelper.getKafkaCallback(cartServiceConfigData.getCartResponseTopicName(),
                            orderAvroModel));

            log.info("OrderAvroModel sent to kafka for cart id: {} and id: {}", cart.getId(), id);
        }catch (Exception e){
            log.error("Error while sending OrderAvroModel to Kafka with cart id: {} and id: {}, error; {}", cart.getId(), id, e.getMessage());
        }

    }
}
