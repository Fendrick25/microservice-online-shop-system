package com.online.shop.system.cart.service.messaging.mapper;

import com.online.shop.system.cart.service.domain.dto.message.User;
import com.online.shop.system.kafka.avro.model.UserAvroModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CartMessagingDataMapper {

    public User userAvroModelToUser(UserAvroModel userAvroModel){
        return User.builder()
                .userID(UUID.fromString(userAvroModel.getUserID()))
                .build();
    }
}
