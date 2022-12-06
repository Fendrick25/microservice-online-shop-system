package com.online.shop.system.order.service.domain;

import com.online.shop.system.order.service.domain.dto.create.CreateOrder;
import com.online.shop.system.order.service.domain.dto.create.GetOrderByDate;
import com.online.shop.system.order.service.domain.dto.create.GetOrderByStatus;
import com.online.shop.system.order.service.domain.dto.create.response.CreateOrderResponse;
import com.online.shop.system.order.service.domain.dto.create.response.GetOrderResponse;
import com.online.shop.system.order.service.domain.dto.create.response.PagingResponse;
import com.online.shop.system.order.service.domain.dto.message.CartOrderResponse;
import com.online.shop.system.order.service.domain.dto.message.PaymentRequest;
import com.online.shop.system.order.service.domain.dto.message.UpdateOrderDetail;
import com.online.shop.system.order.service.domain.entity.Order;
import com.online.shop.system.order.service.domain.entity.OrderDetail;
import com.online.shop.system.order.service.domain.event.OrderCancelledEvent;
import com.online.shop.system.order.service.domain.mapper.OrderDataMapper;
import com.online.shop.system.order.service.domain.ports.input.service.OrderApplicationService;
import com.online.shop.system.order.service.domain.ports.output.message.publisher.CartMessagePublisher;
import com.online.shop.system.order.service.domain.ports.output.message.publisher.PaymentMessagePublisher;
import com.online.shop.system.order.service.domain.ports.output.repository.OrderRepository;
import com.online.shop.system.order.service.domain.valueobject.OrderStatus;
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

    private final CartMessagePublisher cartMessagePublisher;
    private final OrderDomainService orderDomainService;

    @Override
    public CreateOrderResponse requestOrder(CreateOrder createOrder) {
        Order order = orderDomainService.initializeOrder(orderDataMapper.createOrderToOrder(createOrder));
        orderRepository.createOrder(order);
        cartMessagePublisher.publish(createOrder);
        log.info("Order with id: {} created", order.getId());
        return CreateOrderResponse.builder()
                .orderID(order.getId())
                .orderStatus(OrderStatus.PENDING)
                .build();
    }

    @Override
    public void createOrder(CartOrderResponse cartOrderResponse) {
        orderDomainService.orderPaid(cartOrderResponse.getOrder());
        UUID id = orderRepository.updateOrder(cartOrderResponse.getOrder());
        paymentMessagePublisher.requestPayment(PaymentRequest.builder()
                        .orderID(id)
                        .price(cartOrderResponse.getOrder().getTotalPrice())
                .build());
        log.info("Order with id: {} updated", id);
    }

    @Override
    public GetOrderResponse getOrder(UUID orderID) {
        Order order = orderRepository.getOrder(orderID);
        log.info("Order with id: {} found", order.getId());
        return orderDataMapper.orderToGetOrderResponse(order);
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
        OrderDetail orderDetail = orderDomainService.orderCancelled(order, updateOrderDetail.getMessage());
        orderRepository.updateOrderDetail(orderDetail);
        paymentMessagePublisher.cancelPayment(OrderCancelledEvent.builder()
                        .orderID(order.getId())
                        .orderStatus(orderDetail.getOrderStatus())
                .build());
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
