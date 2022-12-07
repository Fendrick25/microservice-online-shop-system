package com.online.shop.system.order.service.dataaccess.adapter;

import com.online.shop.system.order.service.dataaccess.entity.OrderDetailEntity;
import com.online.shop.system.order.service.dataaccess.entity.OrderEntity;
import com.online.shop.system.order.service.dataaccess.entity.UserEntity;
import com.online.shop.system.order.service.dataaccess.exception.ResourceNotFoundException;
import com.online.shop.system.order.service.dataaccess.mapper.OrderDataAccessMapper;
import com.online.shop.system.order.service.dataaccess.repository.OrderDetailJpaRepository;
import com.online.shop.system.order.service.dataaccess.repository.OrderJpaRepository;
import com.online.shop.system.order.service.dataaccess.repository.UserJpaRepository;
import com.online.shop.system.order.service.domain.dto.create.response.PagingResponse;
import com.online.shop.system.order.service.domain.entity.Order;
import com.online.shop.system.order.service.domain.entity.OrderDetail;
import com.online.shop.system.order.service.domain.ports.output.repository.OrderRepository;
import com.online.shop.system.order.service.domain.valueobject.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final OrderDetailJpaRepository orderDetailJpaRepository;
    private final OrderDataAccessMapper orderDataAccessMapper;

    @Override
    public UUID createOrder(Order order) {
        OrderEntity orderEntity = orderDataAccessMapper.orderToOrderEntity(order);
        UserEntity userEntity = userJpaRepository.findById(order.getUserID()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        orderEntity.setUser(userEntity);
        return orderJpaRepository.save(orderEntity).getId();
    }

    @Override
    @Transactional
    public Order getOrder(UUID orderID) {
        return orderDataAccessMapper.orderEntityToOrder(findOrder(orderID));
    }

    @Override
    @Transactional
    public UUID updateOrder(Order order) {
        OrderEntity orderEntity = findOrder(order.getId());
        OrderEntity orderEntityUpdated = orderDataAccessMapper.orderToOrderEntity(order);
        orderEntityUpdated.setId(orderEntity.getId());
        orderEntityUpdated.setUser(orderEntity.getUser());
        return orderJpaRepository.save(orderEntityUpdated).getId();
    }

    @Override
    @Transactional
    public void updateOrderDetail(OrderDetail orderDetail) {
        OrderEntity orderEntity = findOrder(orderDetail.getOrderID());
        OrderDetailEntity orderDetailEntity = orderDataAccessMapper.orderDetailToOrderDetailEntity(orderDetail);
        orderDetailEntity.setOrder(orderEntity);
        orderDetailJpaRepository.save(orderDetailEntity);
    }

    @Override
    @Transactional
    public PagingResponse getAllOrder(UUID userID, int page, int size) {
        Pageable paging = PageRequest.of(page - 1, size);
        Page<OrderEntity> pageOrders = orderJpaRepository.findByUserId(userID, paging);
        return PagingResponse.builder()
                .data(pageOrders.getContent().stream().map(orderDataAccessMapper::orderEntityToOrder).collect(Collectors.toList()))
                .currentPage(pageOrders.getNumber() + 1)
                .size(pageOrders.getSize())
                .total((int) pageOrders.getTotalElements())
                .totalPages(pageOrders.getTotalPages())
                .build();
    }

    @Override
    public PagingResponse getOrderByDate(UUID userID, Date startDate, Date endDate, int page, int size) {
        Pageable paging = PageRequest.of(page - 1, size);
        Page<OrderEntity> pageOrders = orderJpaRepository.findByUserIdAndPurchaseDateBetween(userID, startDate, endDate, paging);
        return PagingResponse.builder()
                .data(pageOrders.getContent().stream().map(orderDataAccessMapper::orderEntityToOrder).collect(Collectors.toList()))
                .currentPage(pageOrders.getNumber() + 1)
                .size(pageOrders.getSize())
                .total((int) pageOrders.getTotalElements())
                .totalPages(pageOrders.getTotalPages())
                .build();
    }

    @Override
    public PagingResponse getOrderByStatus(UUID userID, OrderStatus orderStatus, int page, int size) {
        Pageable paging = PageRequest.of(page - 1, size);
        Page<OrderEntity> pageOrders = orderJpaRepository.findByUserIdAndOrderDetailsOrderStatus(userID, orderStatus, paging);
        return PagingResponse.builder()
                .data(pageOrders.getContent().stream().map(orderDataAccessMapper::orderEntityToOrder).collect(Collectors.toList()))
                .currentPage(pageOrders.getNumber() + 1)
                .size(pageOrders.getSize())
                .total((int) pageOrders.getTotalElements())
                .totalPages(pageOrders.getTotalPages())
                .build();
    }

    @Transactional
    private OrderEntity findOrder(UUID orderID){
        return orderJpaRepository.findById(orderID).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }
}
