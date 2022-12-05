package com.online.shop.system.order.service.dataaccess.mapper;

import com.online.shop.system.order.service.dataaccess.entity.*;
import com.online.shop.system.order.service.domain.entity.*;
import org.springframework.stereotype.Component;

import java.util.Stack;
import java.util.stream.Collectors;

@Component
public class OrderDataAccessMapper {
    public OrderEntity orderToOrderEntity(Order order){
        return OrderEntity.builder()
                .id(order.getId())
                .price(order.getTotalPrice())
                .purchaseDate(order.getPurchaseDate())
                .user(UserEntity.builder()
                        .id(order.getId())
                        .build())
                .orderItems(order.getItems().stream().map(this::orderItemToOrderItemEntity)
                        .collect(Collectors.toList()))
                .orderAddress(order.getAddress())
                .orderDetails(order.getDetails().stream().map(this::orderDetailToOrderDetailEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    public Order orderEntityToOrder(OrderEntity orderEntity){
        return Order.builder()
                .id(orderEntity.getId())
                .userID(orderEntity.getUser().getId())
                .items(orderEntity.getOrderItems().stream().map(this::orderItemEntityToOrderItem)
                        .collect(Collectors.toList()))
                .totalPrice(orderEntity.getPrice())
                .purchaseDate(orderEntity.getPurchaseDate())
                .address(orderEntity.getOrderAddress())
                .details(orderEntity.getOrderDetails().stream().map(this::orderDetailEntityToOrderDetail)
                        .collect(Collectors.toCollection(Stack::new)))
                .build();
    }

    public OrderDetailEntity orderDetailToOrderDetailEntity(OrderDetail orderDetail){
        return OrderDetailEntity.builder()
                .id(orderDetail.getId())
                .orderStatus(orderDetail.getOrderStatus())
                .createdAt(orderDetail.getCreatedAt())
                .message(orderDetail.getMessage())
                .order(OrderEntity.builder()
                        .id(orderDetail.getOrderID())
                        .build())
                .build();
    }

    public UserEntity userToUserEntity(User user){
        return UserEntity.builder()
                .id(user.getId())
                .build();
    }
    private OrderDetail orderDetailEntityToOrderDetail(OrderDetailEntity orderDetailEntity){
        return OrderDetail.builder()
                .id(orderDetailEntity.getId())
                .orderID(orderDetailEntity.getOrder().getId())
                .orderStatus(orderDetailEntity.getOrderStatus())
                .message(orderDetailEntity.getMessage())
                .createdAt(orderDetailEntity.getCreatedAt())
                .build();
    }

    private OrderItem orderItemEntityToOrderItem(OrderItemEntity orderItemEntity){
        return OrderItem.builder()
                .id(orderItemEntity.getId())
                .product(Product.builder()
                        .id(orderItemEntity.getProduct().getId())
                        .name(orderItemEntity.getProduct().getName())
                        .description(orderItemEntity.getProduct().getDescription())
                        .price(orderItemEntity.getProduct().getPrice())
                        .imageUrl(orderItemEntity.getProduct().getImageUrl())
                        .build())
                .quantity(orderItemEntity.getQuantity())
                .subTotal(orderItemEntity.getSubTotal())
                .build();
    }

    private OrderItemEntity orderItemToOrderItemEntity(OrderItem orderItem){
        return OrderItemEntity.builder()
                .id(orderItem.getId())
                .product(ProductEntity.builder()
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


}
