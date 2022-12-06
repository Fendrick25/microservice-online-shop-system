package com.online.shop.system.order.service.domain;

import com.online.shop.system.order.service.domain.entity.Order;
import com.online.shop.system.order.service.domain.entity.OrderDetail;


public interface OrderDomainService {
    Order initializeOrder(Order order);
    void orderVerified(Order order);
    OrderDetail orderPaid(Order order);
    OrderDetail orderShipped(Order order);
    OrderDetail orderArrived(Order order, String message);
    OrderDetail orderConfirmed(Order order);
    OrderDetail orderFinished(Order order);
    OrderDetail orderCancelled(Order order, String message);
}
