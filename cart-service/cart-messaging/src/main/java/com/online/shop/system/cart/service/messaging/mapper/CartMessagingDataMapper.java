package com.online.shop.system.cart.service.messaging.mapper;

import com.online.shop.system.cart.service.domain.dto.message.CartRequest;
import com.online.shop.system.cart.service.domain.dto.message.User;
import com.online.shop.system.cart.service.domain.entity.Cart;
import com.online.shop.system.cart.service.domain.entity.CartItem;
import com.online.shop.system.kafka.avro.model.*;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CartMessagingDataMapper {

    public User userAvroModelToUser(UserAvroModel userAvroModel){
        return User.builder()
                .userID(UUID.fromString(userAvroModel.getUserID()))
                .build();
    }

    public OrderAvroModel cartToOrderAvroModel(Cart cart) {
        return OrderAvroModel.newBuilder()
                .setUserID(cart.getUserID().toString())
                .setTotalPrice(cart.getTotalPrice())
                .setItems(cart.getItems().stream().map(this::cartItemToOrderItem)
                        .collect(Collectors.toList()))
                .build();
    }

    public CartRequest cartRequestAvroModelToCartRequest(CartRequestAvroModel cartRequestAvroModel){
        return CartRequest.builder()
                .cartID(UUID.fromString(cartRequestAvroModel.getCartID()))
                .userID(UUID.fromString(cartRequestAvroModel.getUserID()))
                .build();
    }

    private OrderItem cartItemToOrderItem(CartItem cartItem){
        return OrderItem.newBuilder()
                .setId(cartItem.getId())
                .setProduct(Product.newBuilder()
                        .setId(cartItem.getProduct().getId().toString())
                        .setName(cartItem.getProduct().getName())
                        .setDescription(cartItem.getProduct().getDescription())
                        .setPrice(cartItem.getProduct().getPrice())
                        .setImageUrl(cartItem.getProduct().getImageUrl())
                        .build())
                .setQuantity(cartItem.getQuantity())
                .setSubTotal(cartItem.getSubTotal())
                .build();
    }
}
