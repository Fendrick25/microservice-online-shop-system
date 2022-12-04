package com.online.shop.system.user.service.messaging.publisher.kafka;

import com.online.shop.system.kafka.avro.model.UserAvroModel;
import com.online.shop.system.kafka.producer.KafkaMessageHelper;
import com.online.shop.system.kafka.producer.service.KafkaProducer;
import com.online.shop.system.user.service.domain.config.UserServiceConfigData;
import com.online.shop.system.user.service.domain.dto.message.UserCreatedEvent;
import com.online.shop.system.user.service.domain.ports.output.message.publisher.UserMessagePublisher;
import com.online.shop.system.user.service.messaging.mapper.UserMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserMessagePublisherImpl implements UserMessagePublisher {

    private final KafkaProducer<String, UserAvroModel> kafkaProducer;
    private final UserServiceConfigData userServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;
    private final UserMessagingDataMapper userMessagingDataMapper;

    @Override
    public void publish(UserCreatedEvent userCreatedEvent) {
        String id = UUID.randomUUID().toString();
        UserAvroModel userAvroModel = userMessagingDataMapper.userCreatedEventToUserAvroModel(userCreatedEvent);

        try{
            kafkaProducer.send(userServiceConfigData.getUserTopicName(),
                    id,
                    userAvroModel,
                    kafkaMessageHelper.getKafkaCallback(userServiceConfigData.getUserTopicName(),
                            userAvroModel));

            log.info("UserAvroModel sent to Kafka for user id: {} and id: {}", userAvroModel.getUserID(), id);
        }catch (Exception e){
            log.error("Error while sending UserAvroModel to Kafka with user id: {} and id: {}, error: {}", userAvroModel.getUserID(),
                    id, e.getMessage());
        }
    }
}
