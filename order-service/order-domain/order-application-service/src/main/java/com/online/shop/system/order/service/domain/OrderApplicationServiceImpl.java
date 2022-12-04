package com.online.shop.system.order.service.domain;

import com.online.shop.system.order.service.domain.dto.create.GetOrderByDate;
import com.online.shop.system.order.service.domain.dto.create.GetOrderByStatus;
import com.online.shop.system.order.service.domain.dto.create.response.CreateOrderResponse;
import com.online.shop.system.order.service.domain.dto.create.response.GetOrderResponse;
import com.online.shop.system.order.service.domain.dto.create.response.PagingResponse;
import com.online.shop.system.order.service.domain.dto.message.CreateOrder;
import com.online.shop.system.order.service.domain.dto.message.UpdateOrderDetail;
import com.online.shop.system.order.service.domain.entity.Order;
import com.online.shop.system.order.service.domain.event.OrderEvent;
import com.online.shop.system.order.service.domain.mapper.OrderDataMapper;
import com.online.shop.system.order.service.domain.ports.input.service.OrderApplicationService;
import com.online.shop.system.order.service.domain.ports.output.message.publisher.PaymentMessagePublisher;
import com.online.shop.system.order.service.domain.ports.output.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Service
@Validated
@Slf4j
@RequiredArgsConstructor
public class OrderApplicationServiceImpl implements OrderApplicationService {

    private final OrderDataMapper orderDataMapper;
    private final OrderRepository orderRepository;
    private final PaymentMessagePublisher paymentMessagePublisher;
    private final OrderDomainService orderDomainService;

    @Override
    public CreateOrderResponse createOrder(CreateOrder createOrder) {
        OrderEvent orderEvent = orderDomainService.initializeOrder(createOrder.getOrder());
        UUID orderID = orderRepository.createOrder(orderEvent.getOrder());
        paymentMessagePublisher.publish(orderEvent);

        log.info("Order with id: {} created", orderID);
        return CreateOrderResponse.builder()
                .orderID(orderID)
                .build();
    }

    @Override
    public GetOrderResponse getOrder(UUID orderID) {
        Order order = orderRepository.getOrder(orderID);
        log.info("Order with id: {} found", order.getId());
        return orderDataMapper.orderToGetOrderResponse(order);
    }

    @Override
    public void orderPaid(UpdateOrderDetail updateOrderDetail) {
        Order order = orderRepository.getOrder(updateOrderDetail.getOrderID());
        orderRepository.updateOrderDetail(orderDomainService.orderPaid(order));
        log.info("Order with id: {} paid", order.getId());
    }

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

    @Override
    public void orderFinished(UpdateOrderDetail updateOrderDetail) {
        Order order = orderRepository.getOrder(updateOrderDetail.getOrderID());
        orderRepository.updateOrderDetail(orderDomainService.orderFinished(order));
        log.info("Order with id: {} finished", order.getId());
    }

    @Override
    public void orderCancelled(UpdateOrderDetail updateOrderDetail) {
        Order order = orderRepository.getOrder(updateOrderDetail.getOrderID());
        OrderEvent orderEvent = orderDomainService.orderCancelled(order, updateOrderDetail.getMessage());
        orderRepository.updateOrderDetail(orderEvent.getOrder().getDetails().peek());
        paymentMessagePublisher.publish(orderEvent);
        log.info("Order with id: {} cancelled", order.getId());
    }

    @Override
    public PagingResponse getAllOrder(UUID userID, int page, int size) {
        PagingResponse response = orderRepository.getAllOrder(userID, page, size);
        log.info("Order with user id: {} found", userID);
        return response;
    }

    @Override
    public PagingResponse getOrderByDate(GetOrderByDate getOrderByDate, int page, int size) {
        PagingResponse response = orderRepository.getOrderByDate(getOrderByDate.getUserID(), getOrderByDate.getStartDate(), getOrderByDate.getEndDate(), page, size);
        log.info("Order with user id: {} found", getOrderByDate.getUserID());
        return response;
    }

    @Override
    public PagingResponse getOrderByStatus(GetOrderByStatus getOrderByStatus, int page, int size) {
        PagingResponse response = orderRepository.getOrderByStatus(getOrderByStatus.getUserID(), getOrderByStatus.getOrderStatus(), page, size);
        log.info("Order with user id: {} found", getOrderByStatus.getUserID());
        return response;
    }
}
