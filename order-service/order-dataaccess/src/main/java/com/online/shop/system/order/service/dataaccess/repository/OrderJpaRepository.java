package com.online.shop.system.order.service.dataaccess.repository;

import com.online.shop.system.order.service.dataaccess.entity.OrderEntity;
import com.online.shop.system.order.service.domain.valueobject.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
    Page<OrderEntity> findByUserId(UUID userID, Pageable pageable);
    Page<OrderEntity> findByUserIdAndPurchaseDateBetween(UUID userID, Date startDate, Date endDate, Pageable pageable);
    Page<OrderEntity> findByUserIdAndOrderDetailsOrderStatus(UUID userID, OrderStatus orderStatus, Pageable pageable);
}
