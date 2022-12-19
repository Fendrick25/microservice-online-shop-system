package com.online.shop.system.payment.messaing.publisher;

import com.online.shop.system.kafka.avro.model.PaymentResponseAvroModel;
import com.online.shop.system.kafka.producer.KafkaMessageHelper;
import com.online.shop.system.kafka.producer.service.KafkaProducer;
import com.online.shop.system.payment.messaing.mapper.PaymentMessagingDataMapper;
import com.online.shop.system.payment.service.domain.config.PaymentServiceConfigData;
import com.online.shop.system.payment.service.domain.event.PaymentCancelledEvent;
import com.online.shop.system.payment.service.domain.event.PaymentCreatedEvent;
import com.online.shop.system.payment.service.domain.event.PaymentExpiredEvent;
import com.online.shop.system.payment.service.domain.event.PaymentPaidEvent;
import com.online.shop.system.payment.service.domain.ports.output.message.publisher.OrderMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderMessageKafkaPublisher implements OrderMessagePublisher {

    private final KafkaProducer<String, PaymentResponseAvroModel> kafkaProducer;
    private final KafkaMessageHelper kafkaMessageHelper;
    private final PaymentServiceConfigData paymentServiceConfigData;
    private final PaymentMessagingDataMapper paymentMessagingDataMapper;

    @Override
    public void paymentCreated(PaymentCreatedEvent paymentCreatedEvent) {
        send(paymentMessagingDataMapper.paymentCreatedEventToPaymentResponseAvroModel(paymentCreatedEvent));
    }

    @Override
    public void paymentPaid(PaymentPaidEvent paymentPaidEvent) {
        send(paymentMessagingDataMapper.paymentPaidEventToPaymentResponseAvroModel(paymentPaidEvent));
    }

    @Override
    public void paymentCancelled(PaymentCancelledEvent paymentCancelledEvent) {
        send(paymentMessagingDataMapper.paymentCancelledEventToPaymentResponseAvroModel(paymentCancelledEvent));
    }

    @Override
    public void paymentExpired(PaymentExpiredEvent paymentExpiredEvent) {
        send(paymentMessagingDataMapper.paymentExpiredEventToPaymentResponseAvroModel(paymentExpiredEvent));
    }

    private void send(PaymentResponseAvroModel paymentResponseAvroModel){
        String id = UUID.randomUUID().toString();
        try{
            kafkaProducer.send(paymentServiceConfigData.getPaymentResponseTopicName(),
                    id,
                    paymentResponseAvroModel,
                    kafkaMessageHelper.getKafkaCallback(paymentServiceConfigData.getPaymentResponseTopicName(),
                            paymentResponseAvroModel));

            log.info("PaymentResponseAvroModel sent to kafka for order id: {} and id: {}", paymentResponseAvroModel.getOrderID(), id);
        }catch (Exception e){
            log.error("Error while sending PaymentResponseAvroModel to Kafka with cart id: {} and id: {}, error; {}", paymentResponseAvroModel.getOrderID(), id, e.getMessage());
        }
    }
}
