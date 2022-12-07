package com.online.shop.system.order.service.messaging.publisher.kafka;

import com.online.shop.system.kafka.avro.model.ShippingRequestAvroModel;
import com.online.shop.system.kafka.producer.KafkaMessageHelper;
import com.online.shop.system.kafka.producer.service.KafkaProducer;
import com.online.shop.system.order.service.domain.config.OrderServiceConfigData;
import com.online.shop.system.order.service.domain.event.OrderPaidEvent;
import com.online.shop.system.order.service.domain.ports.output.message.publisher.ShippingMessagePublisher;
import com.online.shop.system.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShippingMessageKafkaPublisher implements ShippingMessagePublisher {

    private final KafkaProducer<String, ShippingRequestAvroModel> kafkaProducer;
    private final OrderServiceConfigData orderServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;
    private final OrderMessagingDataMapper orderMessagingDataMapper;

    @Override
    public void shipOrder(OrderPaidEvent orderPaidEvent) {

        String id = UUID.randomUUID().toString();
        ShippingRequestAvroModel shippingRequestAvroModel = orderMessagingDataMapper.orderPaidEventToShippingRequestAvroModel(orderPaidEvent);

        try{
            kafkaProducer.send(orderServiceConfigData.getShippingRequestTopicName(),
                    id,
                    shippingRequestAvroModel,
                    kafkaMessageHelper.getKafkaCallback(orderServiceConfigData.getShippingRequestTopicName(),
                            shippingRequestAvroModel));

            log.info("ShippingRequestAvroModel sent to kafka for order id: {} and id: {}", shippingRequestAvroModel.getOrderID(), id);
        }catch (Exception e){
            log.error("Error while sending ShippingRequestAvroModel to Kafka with order id: {} and id: {}, error; {}", shippingRequestAvroModel.getOrderID(), id, e.getMessage());
        }
    }
}
