package com.online.shop.system.order.service.domain;

import com.online.shop.system.order.service.domain.dto.message.UpdateOrderDetail;
import com.online.shop.system.order.service.domain.entity.Order;
import com.online.shop.system.order.service.domain.ports.input.message.listener.ShippingMessageListener;
import com.online.shop.system.order.service.domain.ports.output.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShippingMessageListenerImpl implements ShippingMessageListener {

    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;

    @Override
    public void orderShipped(UpdateOrderDetail updateOrderDetail) {
        Order order = orderRepository.getOrder(updateOrderDetail.getOrderID());
        orderRepository.updateOrderDetail(orderDomainService.orderShipped(order));
        log.info("Order with id: {} shipped", order.getId());
    }

    @Override
    public void orderArrived(UpdateOrderDetail updateOrderDetail) {
        Order order = orderRepository.getOrder(updateOrderDetail.getOrderID());
        orderRepository.updateOrderDetail(orderDomainService.orderArrived(order, updateOrderDetail.getMessage()));
        log.info("Order with id: {} arrived", order.getId());
    }

    @Override
    public void orderConfirmed(UpdateOrderDetail updateOrderDetail) {
        Order order = orderRepository.getOrder(updateOrderDetail.getOrderID());
        orderRepository.updateOrderDetail(orderDomainService.orderConfirmed(order));
        log.info("Order with id: {} confirmed", order.getId());
    }
}
