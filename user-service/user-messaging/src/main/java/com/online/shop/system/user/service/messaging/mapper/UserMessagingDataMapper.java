package com.online.shop.system.user.service.messaging.mapper;

import com.online.shop.system.kafka.avro.model.UserAvroModel;
import com.online.shop.system.user.service.domain.dto.message.UserCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class UserMessagingDataMapper {

    public UserAvroModel userCreatedEventToUserAvroModel(UserCreatedEvent userCreatedEvent){
        return UserAvroModel.newBuilder()
                .setUserID(userCreatedEvent.getUserID().toString())
                .build();
    }
}
