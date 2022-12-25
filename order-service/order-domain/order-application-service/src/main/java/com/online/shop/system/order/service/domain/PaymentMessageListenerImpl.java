package com.online.shop.system.order.service.domain;

import com.online.shop.system.order.service.domain.dto.message.UpdateOrderDetail;
import com.online.shop.system.order.service.domain.dto.message.UpdateProductStock;
import com.online.shop.system.order.service.domain.entity.Order;
import com.online.shop.system.order.service.domain.entity.OrderDetail;
import com.online.shop.system.order.service.domain.event.OrderPaidEvent;
import com.online.shop.system.order.service.domain.ports.input.message.listener.PaymentMessageListener;
import com.online.shop.system.order.service.domain.ports.output.message.publisher.ShippingMessagePublisher;
import com.online.shop.system.order.service.domain.ports.output.repository.OrderRepository;
import com.online.shop.system.order.service.domain.valueobject.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentMessageListenerImpl implements PaymentMessageListener {

    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;
    private final ShippingMessagePublisher shippingMessagePublisher;
    private final WebClient.Builder webClient;

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
        webClient.build().method(HttpMethod.PUT)
                .uri("http://product-service/api/v1/products/stocks")
                .body(BodyInserters.fromValue(UpdateProductStock.builder()
                        .products(order.getItems().stream().map(orderItem ->
                                UpdateProductStock.Product.builder()
                                        .productID(orderItem.getProduct().getId())
                                        .quantity(orderItem.getQuantity())
                                        .build()).collect(Collectors.toList()))
                        .orderStatus(OrderStatus.CANCELLED)
                        .build()))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        log.info("Order with id: {} expired", order.getId());
    }

    @Override
    public void paymentCreated(UpdateOrderDetail updateOrderDetail) {
        Order order = orderRepository.getOrder(updateOrderDetail.getOrderID());
        OrderDetail orderDetail = orderDomainService.paymentCreated(order);
        orderRepository.updateOrderDetail(orderDetail);
        webClient.build().method(HttpMethod.PUT)
                .uri("http://product-service/api/v1/products/stocks")
                .body(BodyInserters.fromValue(UpdateProductStock.builder()
                        .products(order.getItems().stream().map(orderItem ->
                                UpdateProductStock.Product.builder()
                                        .productID(orderItem.getProduct().getId())
                                        .quantity(orderItem.getQuantity())
                                        .build()).collect(Collectors.toList()))
                        .orderStatus(orderDetail.getOrderStatus())
                        .build()))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        log.info("Payment created for order with id: {}", order.getId());
    }
}
