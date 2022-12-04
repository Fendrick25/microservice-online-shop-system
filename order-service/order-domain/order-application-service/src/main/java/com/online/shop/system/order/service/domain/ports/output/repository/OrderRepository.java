package com.online.shop.system.order.service.domain.ports.output.repository;


import com.online.shop.system.order.service.domain.dto.create.response.PagingResponse;
import com.online.shop.system.order.service.domain.entity.Order;
import com.online.shop.system.order.service.domain.entity.OrderDetail;
import com.online.shop.system.order.service.domain.valueobject.OrderStatus;

import java.util.Date;
import java.util.UUID;

public interface OrderRepository {
    UUID createOrder(Order order);
    Order getOrder(UUID orderID);
    void updateOrderDetail(OrderDetail orderDetail);
    PagingResponse getAllOrder(UUID userID, int page, int size);
    PagingResponse getOrderByDate(UUID userID, Date startDate, Date endDate, int page, int size);
    PagingResponse getOrderByStatus(UUID userID, OrderStatus orderStatus, int page, int size);

}
