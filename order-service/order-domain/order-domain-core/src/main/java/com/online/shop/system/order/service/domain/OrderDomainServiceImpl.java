package com.online.shop.system.order.service.domain;

import com.online.shop.system.order.service.domain.entity.Order;
import com.online.shop.system.order.service.domain.entity.OrderDetail;
import com.online.shop.system.order.service.domain.entity.event.OrderCancelledEvent;
import com.online.shop.system.order.service.domain.entity.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService{

    @Override
    public OrderCreatedEvent initializeOrder(Order order) {
        order.initializeOrder();
        log.info("Order with id: " + order.getId() + " initialized");
        return OrderCreatedEvent.builder()
                .order(order)
                .createdAt(ZonedDateTime.now(ZoneId.of("UTC")))
                .build();
    }

    @Override
    public OrderDetail orderPaid(Order order) {
        OrderDetail orderDetail = order.payOrder();
        log.info("Order with id: " + order.getId() + " paid");
        return orderDetail;
    }

    @Override
    public OrderDetail orderShipped(Order order) {
        OrderDetail orderDetail = order.shipOrder();
        log.info("Order with id: " + order.getId() + " shipped");
        return orderDetail;
    }

    @Override
    public OrderDetail orderArrived(Order order, String message) {
        OrderDetail orderDetail = order.orderArrived(message);
        log.info("Order with id: " + order.getId() + " arrived");
        return orderDetail;
    }

    @Override
    public OrderDetail orderConfirmed(Order order) {
        OrderDetail orderDetail = order.confirmOrder();
        log.info("Order with id: " + order.getId() + " confirmed");
        return orderDetail;
    }

    @Override
    public OrderDetail orderFinished(Order order) {
        OrderDetail orderDetail = order.finishOrder();
        log.info("Order with id: " + order.getId() + " finished");
        return orderDetail;
    }

    @Override
    public OrderCancelledEvent orderCancelled(Order order, String message) {
        Order updatedOrder = order.cancelOrder(order, message);
        log.info("Order with id: " + order.getId() + " cancelled");
        return OrderCancelledEvent.builder()
                .order(updatedOrder)
                .createdAt(ZonedDateTime.now(ZoneId.of("UTC")))
                .build();
    }
}
