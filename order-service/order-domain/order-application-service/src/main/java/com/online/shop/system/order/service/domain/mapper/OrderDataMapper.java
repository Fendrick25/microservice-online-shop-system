package com.online.shop.system.order.service.domain.mapper;

import com.online.shop.system.order.service.domain.dto.create.response.GetOrderResponse;
import com.online.shop.system.order.service.domain.entity.Order;
import com.online.shop.system.order.service.domain.entity.OrderDetail;
import com.online.shop.system.order.service.domain.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderDataMapper {
    public GetOrderResponse orderToGetOrderResponse(Order order){
        return GetOrderResponse.builder()
                .orderID(order.getId())
                .userID(order.getUserID())
                .items(order.getItems().stream().map(this::orderItemToGetOrderResponseOrderItem)
                        .collect(Collectors.toList()))
                .totalPrice(order.getTotalPrice())
                .orderAddress(order.getAddress())
                .details(order.getDetails().stream().map(this::orderDetailToGetOrderResponseOrderDetail)
                        .collect(Collectors.toList()))
                .build();
    }

    private GetOrderResponse.OrderItem orderItemToGetOrderResponseOrderItem(OrderItem orderItem){
        return GetOrderResponse.OrderItem.builder()
                .orderItemID(orderItem.getId())
                .product(GetOrderResponse.Product.builder()
                        .id(orderItem.getProduct().getId())
                        .name(orderItem.getProduct().getName())
                        .description(orderItem.getProduct().getDescription())
                        .price(orderItem.getProduct().getPrice())
                        .imageUrl(orderItem.getProduct().getImageUrl())
                        .build())
                .quantity(orderItem.getQuantity())
                .subTotal(orderItem.getSubTotal())
                .build();
    }

    private GetOrderResponse.OrderDetail orderDetailToGetOrderResponseOrderDetail(OrderDetail orderDetail){
        return GetOrderResponse.OrderDetail.builder()
                .id(orderDetail.getId())
                .orderStatus(orderDetail.getOrderStatus())
                .createdAt(orderDetail.getCreatedAt())
                .message(orderDetail.getMessage())
                .build();
    }
}
