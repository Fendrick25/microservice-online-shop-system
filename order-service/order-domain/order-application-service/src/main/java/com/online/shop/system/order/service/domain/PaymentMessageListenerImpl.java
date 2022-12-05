package com.online.shop.system.order.service.domain;

import com.online.shop.system.order.service.domain.dto.message.UpdateOrderDetail;
import com.online.shop.system.order.service.domain.entity.Order;
import com.online.shop.system.order.service.domain.ports.input.message.listener.PaymentMessageListener;
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


    @Override
    public void updateOrderStatus(UpdateOrderDetail updateOrderDetail) {
        Order order = orderRepository.getOrder(updateOrderDetail.getOrderID());
        orderRepository.updateOrderDetail(orderDomainService.orderPaid(order));
        log.info("Order with id: {} paid", order.getId());
    }
}
