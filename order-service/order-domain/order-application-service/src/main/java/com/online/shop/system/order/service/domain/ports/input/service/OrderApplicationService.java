package com.online.shop.system.order.service.domain.ports.input.service;

import com.online.shop.system.order.service.domain.dto.create.CreateOrder;
import com.online.shop.system.order.service.domain.dto.create.GetOrderByDate;
import com.online.shop.system.order.service.domain.dto.create.GetOrderByStatus;
import com.online.shop.system.order.service.domain.dto.create.response.PagingResponse;
import com.online.shop.system.order.service.domain.dto.message.UpdateOrderDetail;
import com.online.shop.system.order.service.domain.dto.create.response.CreateOrderResponse;
import com.online.shop.system.order.service.domain.dto.create.response.GetOrderResponse;
import com.online.shop.system.order.service.domain.dto.message.CartOrderResponse;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

public interface OrderApplicationService {
    CreateOrderResponse requestOrder(@Validated CreateOrder createOrder);
    void createOrder(CartOrderResponse cartOrderResponse);
    GetOrderResponse getOrder(UUID orderID);
    void orderFinished(@Validated UpdateOrderDetail updateOrderDetail);
    void orderCancelled(@Validated UpdateOrderDetail updateOrderDetail);
    PagingResponse getAllOrder(UUID userID, int page, int size);
    PagingResponse getOrderByDate(@Validated GetOrderByDate getOrderByDate, int page, int size);
    PagingResponse getOrderByStatus(@Validated GetOrderByStatus getOrderByStatus, int page, int size);
}
