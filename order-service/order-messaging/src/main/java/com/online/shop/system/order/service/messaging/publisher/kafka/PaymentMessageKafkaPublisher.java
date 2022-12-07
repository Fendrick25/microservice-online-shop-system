package com.online.shop.system.order.service.messaging.publisher.kafka;

import com.online.shop.system.kafka.avro.model.PaymentRequestAvroModel;
import com.online.shop.system.kafka.producer.KafkaMessageHelper;
import com.online.shop.system.kafka.producer.service.KafkaProducer;
import com.online.shop.system.order.service.domain.config.OrderServiceConfigData;
import com.online.shop.system.order.service.domain.dto.message.PaymentRequest;
import com.online.shop.system.order.service.domain.event.OrderCancelledEvent;
import com.online.shop.system.order.service.domain.ports.output.message.publisher.PaymentMessagePublisher;
import com.online.shop.system.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentMessageKafkaPublisher implements PaymentMessagePublisher {

    private final KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;
    private final OrderServiceConfigData orderServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;
    private final OrderMessagingDataMapper orderMessagingDataMapper;


    @Override
    public void requestPayment(PaymentRequest paymentRequest) {
        String id = UUID.randomUUID().toString();
        PaymentRequestAvroModel paymentRequestAvroModel = orderMessagingDataMapper.paymentRequestToPaymentRequestAvroModel(paymentRequest);

        try{
            kafkaProducer.send(orderServiceConfigData.getPaymentRequestTopicName(),
                    id,
                    paymentRequestAvroModel,
                    kafkaMessageHelper.getKafkaCallback(orderServiceConfigData.getPaymentRequestTopicName(),
                            paymentRequestAvroModel));

            log.info("PaymentRequestAvroModel sent to kafka for order id: {} and id: {}", paymentRequestAvroModel.getOrderID(), id);
        }catch (Exception e){
            log.error("Error while sending PaymentRequestAvroModel to Kafka with cart id: {} and id: {}, error; {}", paymentRequestAvroModel.getOrderID(), id, e.getMessage());
        }
    }

    @Override
    public void cancelPayment(OrderCancelledEvent orderCancelledEvent) {
        String id = UUID.randomUUID().toString();
        PaymentRequestAvroModel paymentRequestAvroModel = orderMessagingDataMapper.orderCancelledEventToPaymentRequestAvroModel(orderCancelledEvent);

        try{
            kafkaProducer.send(orderServiceConfigData.getPaymentRequestTopicName(),
                    id,
                    paymentRequestAvroModel,
                    kafkaMessageHelper.getKafkaCallback(orderServiceConfigData.getPaymentRequestTopicName(),
                            paymentRequestAvroModel));

            log.info("PaymentRequestAvroModel sent to kafka for order id: {} and id: {}", paymentRequestAvroModel.getOrderID(), id);
        }catch (Exception e){
            log.error("Error while sending PaymentRequestAvroModel to Kafka with cart id: {} and id: {}, error; {}", paymentRequestAvroModel.getOrderID(), id, e.getMessage());
        }
    }
}
