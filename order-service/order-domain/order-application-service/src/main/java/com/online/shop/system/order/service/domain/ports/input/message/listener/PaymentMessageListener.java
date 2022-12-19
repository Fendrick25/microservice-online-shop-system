package com.online.shop.system.order.service.domain.ports.input.message.listener;

import com.online.shop.system.order.service.domain.dto.message.UpdateOrderDetail;

public interface PaymentMessageListener {
    void orderPaid(UpdateOrderDetail updateOrderDetail);
    void orderExpired(UpdateOrderDetail updateOrderDetail);
    void paymentCreated(UpdateOrderDetail updateOrderDetail);
}
