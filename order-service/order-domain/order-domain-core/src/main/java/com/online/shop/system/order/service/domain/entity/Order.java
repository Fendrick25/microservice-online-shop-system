package com.online.shop.system.order.service.domain.entity;

import com.online.shop.system.order.service.domain.exception.OrderDomainException;
import com.online.shop.system.order.service.domain.valueobject.OrderAddress;
import com.online.shop.system.order.service.domain.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Order {

    private UUID id;
    private final UUID userID;
    private final List<OrderItem> items;
    private final BigDecimal totalPrice;
    private ZonedDateTime purchaseDate;
    private final OrderAddress address;
    private Stack<OrderDetail> details;

    public void initializeOrder(){
        id = UUID.randomUUID();
        purchaseDate = ZonedDateTime.now(ZoneId.of("UTC"));
        details.add(OrderDetail.builder()
                        .id(id.toString().concat("1"))
                        .message("WAITING FOR PAYMENT")
                        .orderStatus(OrderStatus.PENDING)
                        .createdAt(ZonedDateTime.now(ZoneId.of("UTC")))
                .build());
    }

    public OrderDetail payOrder(){
        if(details.peek().getOrderStatus() != OrderStatus.PENDING)
            throw new OrderDomainException("NOT IN CORRECT STATE FOR PAY ORDER");
        return OrderDetail.builder()
                .id(id.toString().concat("2"))
                .orderStatus(OrderStatus.PAID)
                .createdAt(ZonedDateTime.now(ZoneId.of("UTC")))
                .message("Order paid and being processed")
                .build();
    }

    public OrderDetail shipOrder(){
        if(details.peek().getOrderStatus() != OrderStatus.PAID)
            throw new OrderDomainException("NOT IN CORRECT STATE FOR SHIP ORDER");
        return OrderDetail.builder()
                .id(id.toString().concat("3"))
                .orderStatus(OrderStatus.SHIPPED)
                .createdAt(ZonedDateTime.now(ZoneId.of("UTC")))
                .message("Order has been shipped")
                .build();
    }

    public OrderDetail orderArrived(String message){
        if(details.peek().getOrderStatus() != OrderStatus.SHIPPED)
            throw new OrderDomainException("NOT IN CORRECT STATE FOR ARRIVE ORDER");
        return OrderDetail.builder()
                .id(id.toString().concat("4"))
                .orderStatus(OrderStatus.ARRIVED)
                .createdAt(ZonedDateTime.now(ZoneId.of("UTC")))
                .message("Order received by " + message)
                .build();
    }

    public OrderDetail confirmOrder(){
        if(details.peek().getOrderStatus() != OrderStatus.ARRIVED)
            throw new OrderDomainException("NOT IN CORRECT STATE FOR CONFIRM ORDER");
        return OrderDetail.builder()
                .id(id.toString().concat("5"))
                .orderStatus(OrderStatus.CONFIRMED)
                .createdAt(ZonedDateTime.now(ZoneId.of("UTC")))
                .message("Order confirmed by client")
                .build();
    }

    public OrderDetail finishOrder(){
        if(details.peek().getOrderStatus() != OrderStatus.FINISHED)
            throw new OrderDomainException("NOT IN CORRECT STATE FOR FINISH ORDER");
        return OrderDetail.builder()
                .id(id.toString().concat("6"))
                .orderStatus(OrderStatus.FINISHED)
                .createdAt(ZonedDateTime.now(ZoneId.of("UTC")))
                .message("Order finished")
                .build();
    }

    public Order cancelOrder(Order order, String message){
        if(details.peek().getOrderStatus() != OrderStatus.PENDING)
            throw new OrderDomainException("NOT IN CORRECT STATE FOR CANCEL ORDER");
        details.add(OrderDetail.builder()
                .id(id.toString().concat("7"))
                .orderStatus(OrderStatus.CANCELLED)
                .createdAt(ZonedDateTime.now(ZoneId.of("UTC")))
                .message("Order cancelled by " + message)
                .build());

        return order;
    }
}
