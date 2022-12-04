package com.online.shop.system.order.service.domain;

import com.online.shop.system.order.service.domain.entity.Order;
import com.online.shop.system.order.service.domain.entity.OrderDetail;
import com.online.shop.system.order.service.domain.event.OrderEvent;


public interface OrderDomainService {
    OrderEvent initializeOrder(Order order);
    OrderDetail orderPaid(Order order);
    OrderDetail orderShipped(Order order);
    OrderDetail orderArrived(Order order, String message);
    OrderDetail orderConfirmed(Order order);
    OrderDetail orderFinished(Order order);
    OrderEvent orderCancelled(Order order, String message);
}
