package com.online.shop.system.order.service.domain;

import com.online.shop.system.order.service.domain.entity.Order;
import com.online.shop.system.order.service.domain.entity.OrderDetail;
import com.online.shop.system.order.service.domain.entity.event.OrderCancelledEvent;
import com.online.shop.system.order.service.domain.entity.event.OrderCreatedEvent;


public interface OrderDomainService {
    OrderCreatedEvent initializeOrder(Order order);
    OrderDetail orderPaid(Order order);
    OrderDetail orderShipped(Order order);
    OrderDetail orderArrived(Order order, String message);
    OrderDetail orderConfirmed(Order order);
    OrderDetail orderFinished(Order order);
    OrderCancelledEvent orderCancelled(Order order, String message);
}
