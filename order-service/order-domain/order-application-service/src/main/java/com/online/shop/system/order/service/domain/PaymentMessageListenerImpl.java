package com.online.shop.system.order.service.domain;

import com.online.shop.system.order.service.domain.dto.message.UpdateOrderDetail;
import com.online.shop.system.order.service.domain.entity.Order;
import com.online.shop.system.order.service.domain.entity.OrderDetail;
import com.online.shop.system.order.service.domain.event.OrderPaidEvent;
import com.online.shop.system.order.service.domain.ports.input.message.listener.PaymentMessageListener;
import com.online.shop.system.order.service.domain.ports.output.message.publisher.ShippingMessagePublisher;
import com.online.shop.system.order.service.domain.ports.output.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentMessageListenerImpl implements PaymentMessageListener {

    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;
    private final ShippingMessagePublisher shippingMessagePublisher;

    @Override
    public void orderPaid(UpdateOrderDetail updateOrderDetail) {
        Order order = orderRepository.getOrder(updateOrderDetail.getOrderID());
        OrderDetail orderDetail = orderDomainService.orderPaid(order);
        orderRepository.updateOrderDetail(orderDetail);
        shippingMessagePublisher.shipOrder(OrderPaidEvent.builder()
                        .orderID(order.getId())
                        .orderAddress(order.getAddress())
                .build());
        log.info("Order with id: {} paid", order.getId());
    }

    @Override
    public void orderExpired(UpdateOrderDetail updateOrderDetail) {
        Order order = orderRepository.getOrder(updateOrderDetail.getOrderID());
        OrderDetail orderDetail = orderDomainService.orderExpired(order);
        orderRepository.updateOrderDetail(orderDetail);
        log.info("Order with id: {} expired", order.getId());
    }

    @Override
    public void paymentCreated(UpdateOrderDetail updateOrderDetail) {
        Order order = orderRepository.getOrder(updateOrderDetail.getOrderID());
        OrderDetail orderDetail = orderDomainService.paymentCreated(order);
        orderRepository.updateOrderDetail(orderDetail);
        log.info("Payment created for order with id: {}", order.getId());
    }
}
