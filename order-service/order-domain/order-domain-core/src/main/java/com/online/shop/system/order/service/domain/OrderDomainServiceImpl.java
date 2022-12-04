package com.online.shop.system.order.service.domain;

import com.online.shop.system.order.service.domain.entity.Order;
import com.online.shop.system.order.service.domain.entity.OrderDetail;
import com.online.shop.system.order.service.domain.event.OrderEvent;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class OrderDomainServiceImpl implements OrderDomainService{

    @Override
    public OrderEvent initializeOrder(Order order) {
        order.initializeOrder();
        return OrderEvent.builder()
                .order(order)
                .createdAt(ZonedDateTime.now(ZoneId.of("UTC")))
                .build();
    }

    @Override
    public OrderDetail orderPaid(Order order) {
        return order.payOrder();
    }

    @Override
    public OrderDetail orderShipped(Order order) {
        return order.shipOrder();
    }

    @Override
    public OrderDetail orderArrived(Order order, String message) {
        return order.orderArrived(message);
    }

    @Override
    public OrderDetail orderConfirmed(Order order) {
        return order.confirmOrder();
    }

    @Override
    public OrderDetail orderFinished(Order order) {
        return order.finishOrder();
    }

    @Override
    public OrderEvent orderCancelled(Order order, String message) {
        Order updatedOrder = order.cancelOrder(order, message);
        return OrderEvent.builder()
                .order(updatedOrder)
                .createdAt(ZonedDateTime.now(ZoneId.of("UTC")))
                .build();
    }
}
