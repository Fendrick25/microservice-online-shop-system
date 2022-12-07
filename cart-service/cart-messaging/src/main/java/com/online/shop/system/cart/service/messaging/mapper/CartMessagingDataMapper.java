package com.online.shop.system.cart.service.messaging.mapper;

import com.online.shop.system.cart.service.domain.dto.create.response.GetCartResponse;
import com.online.shop.system.cart.service.domain.dto.message.CartOrderResponse;
import com.online.shop.system.cart.service.domain.dto.message.CartRequest;
import com.online.shop.system.cart.service.domain.dto.message.User;
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

    public CartOrderResponseAvroModel cartOrderResponseToCartOrderResponseAvroModel(CartOrderResponse cartOrderResponse) {
        return CartOrderResponseAvroModel.newBuilder()
                .setOrderID(cartOrderResponse.getOrderID().toString())
                .setUserID(cartOrderResponse.getGetCartResponse().getUserID().toString())
                .setTotalPrice(cartOrderResponse.getGetCartResponse().getTotalPrice())
                .setItems(cartOrderResponse.getGetCartResponse().getItems().stream().map(this::getCartResponseItemToOrderItem)
                        .collect(Collectors.toList()))
                .build();
    }

    public CartRequest cartRequestAvroModelToCartRequest(CartRequestAvroModel cartRequestAvroModel){
        return CartRequest.builder()
                .cartID(UUID.fromString(cartRequestAvroModel.getCartID()))
                .orderID(UUID.fromString(cartRequestAvroModel.getOrderID()))
                .userID(UUID.fromString(cartRequestAvroModel.getUserID()))
                .build();
    }

    private OrderItem getCartResponseItemToOrderItem(GetCartResponse.Item item){
        return OrderItem.newBuilder()
                .setId(item.getCartItemID())
                .setProduct(Product.newBuilder()
                        .setId(item.getProduct().getProductID().toString())
                        .setName(item.getProduct().getName())
                        .setDescription(item.getProduct().getDescription())
                        .setPrice(item.getProduct().getPrice())
                        .setImageUrl(item.getProduct().getImageUrl())
                        .build())
                .setQuantity(item.getQuantity())
                .setSubTotal(item.getSubTotal())
                .build();
    }
}
