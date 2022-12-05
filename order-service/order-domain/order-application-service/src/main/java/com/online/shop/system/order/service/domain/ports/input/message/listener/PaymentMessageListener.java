package com.online.shop.system.order.service.domain.ports.input.message.listener;

import com.online.shop.system.order.service.domain.dto.message.UpdateOrderDetail;

public interface PaymentMessageListener {
    void updateOrderStatus(UpdateOrderDetail updateOrderDetail);
}
