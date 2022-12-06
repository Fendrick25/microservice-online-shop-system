package com.online.shop.system.order.service.domain;

import com.online.shop.system.order.service.domain.entity.Order;
import com.online.shop.system.order.service.domain.entity.OrderDetail;

public class OrderDomainServiceImpl implements OrderDomainService{

    @Override
    public Order initializeOrder(Order order) {
        order.initializeOrder();
        return order;
    }

    @Override
    public void orderVerified(Order order) {
        order.verifyOrder();
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
    public OrderDetail orderCancelled(Order order, String message) {
        return order.cancelOrder(order, message);
    }
}
