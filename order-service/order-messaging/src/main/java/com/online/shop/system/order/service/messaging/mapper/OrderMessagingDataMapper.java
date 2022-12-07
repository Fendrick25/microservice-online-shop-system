package com.online.shop.system.order.service.messaging.mapper;

import com.online.shop.system.kafka.avro.model.*;
import com.online.shop.system.order.service.domain.dto.message.CartOrderResponse;
import com.online.shop.system.order.service.domain.dto.message.PaymentRequest;
import com.online.shop.system.order.service.domain.dto.message.UpdateOrderDetail;
import com.online.shop.system.order.service.domain.entity.*;
import com.online.shop.system.order.service.domain.entity.OrderItem;
import com.online.shop.system.order.service.domain.entity.Product;
import com.online.shop.system.order.service.domain.event.OrderCancelledEvent;
import com.online.shop.system.order.service.domain.event.OrderPaidEvent;
import com.online.shop.system.order.service.domain.event.OrderRequestEvent;
import com.online.shop.system.order.service.domain.valueobject.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderMessagingDataMapper {

    public CartOrderResponse cartOrderResponseAvroModelToCartOrderResponse(CartOrderResponseAvroModel cartOrderResponseAvroModel){
        return CartOrderResponse.builder()
                .order(Order.builder()
                        .id(UUID.fromString(cartOrderResponseAvroModel.getOrderID()))
                        .userID(UUID.fromString(cartOrderResponseAvroModel.getUserID()))
                        .items(cartOrderResponseAvroModel.getItems().stream().map(this::cartOrderResponseAvroModelOrderItemToOrderItem).collect(Collectors.toList()))
                        .totalPrice(cartOrderResponseAvroModel.getTotalPrice())
                        .build())
                .build();
    }

    public UpdateOrderDetail paymentResponseAvroModelToUpdateOrderDetail(PaymentResponseAvroModel paymentResponseAvroModel){
        return UpdateOrderDetail.builder()
                .orderID(UUID.fromString(paymentResponseAvroModel.getOrderID()))
                .orderStatus(OrderStatus.valueOf(paymentResponseAvroModel.getPaymentStatus().name()))
                .build();
    }

    public UpdateOrderDetail shippingResponseAvroModelToUpdateOrderDetail(ShippingResponseAvroModel shippingResponseAvroModel){
        return UpdateOrderDetail.builder()
                .orderID(UUID.fromString(shippingResponseAvroModel.getOrderID()))
                .orderStatus(OrderStatus.valueOf(shippingResponseAvroModel.getShippingStatus().name()))
                .build();
    }

    public User userAvroModelToUser(UserAvroModel userAvroModel){
        return User.builder()
                .id(UUID.fromString(userAvroModel.getUserID()))
                .build();
    }

    public CartRequestAvroModel orderRequestEventToCartRequestAvroModel(OrderRequestEvent orderRequestEvent){
        return CartRequestAvroModel.newBuilder()
                .setOrderID(orderRequestEvent.getOrderID().toString())
                .setCartID(orderRequestEvent.getCartID().toString())
                .setUserID(orderRequestEvent.getUserID().toString())
                .build();
    }

    public PaymentRequestAvroModel paymentRequestToPaymentRequestAvroModel(PaymentRequest paymentRequest){
        return PaymentRequestAvroModel.newBuilder()
                .setOrderID(paymentRequest.getOrderID().toString())
                .setPrice(paymentRequest.getPrice())
                .setPaymentStatus(PaymentStatus.valueOf(paymentRequest.getOrderStatus().name()))
                .build();
    }

    public PaymentRequestAvroModel orderCancelledEventToPaymentRequestAvroModel(OrderCancelledEvent orderCancelledEvent){
        return PaymentRequestAvroModel.newBuilder()
                .setOrderID(orderCancelledEvent.getOrderID().toString())
                .setPaymentStatus(PaymentStatus.valueOf(orderCancelledEvent.getOrderStatus().name()))
                .build();
    }

    public ShippingRequestAvroModel orderPaidEventToShippingRequestAvroModel(OrderPaidEvent orderPaidEvent){
        return ShippingRequestAvroModel.newBuilder()
                .setOrderID(orderPaidEvent.getOrderID().toString())
                .setOrderAddress(OrderAddress.newBuilder()
                        .setStreet(orderPaidEvent.getOrderAddress().getStreet())
                        .setPostalCode(orderPaidEvent.getOrderAddress().getPostalCode())
                        .setCity(orderPaidEvent.getOrderAddress().getCity())
                        .build())
                .build();
    }

    private OrderItem cartOrderResponseAvroModelOrderItemToOrderItem(com.online.shop.system.kafka.avro.model.OrderItem orderItem){
        return OrderItem.builder()
                .id(orderItem.getId())
                .product(Product.builder()
                        .id(UUID.fromString(orderItem.getProduct().getId()))
                        .name(orderItem.getProduct().getName())
                        .description(orderItem.getProduct().getDescription())
                        .price(orderItem.getProduct().getPrice())
                        .imageUrl(orderItem.getProduct().getImageUrl())
                        .build())
                .quantity(orderItem.getQuantity())
                .subTotal(orderItem.getSubTotal())
                .build();
    }
}
