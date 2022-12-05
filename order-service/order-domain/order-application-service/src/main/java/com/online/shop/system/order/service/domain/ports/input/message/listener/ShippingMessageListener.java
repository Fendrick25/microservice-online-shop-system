package com.online.shop.system.order.service.domain.ports.input.message.listener;

import com.online.shop.system.order.service.domain.dto.message.UpdateOrderDetail;

public interface ShippingMessageListener {
    void orderShipped(UpdateOrderDetail updateOrderDetail);
    void orderArrived(UpdateOrderDetail updateOrderDetail);
    void orderConfirmed(UpdateOrderDetail updateOrderDetail);
}
