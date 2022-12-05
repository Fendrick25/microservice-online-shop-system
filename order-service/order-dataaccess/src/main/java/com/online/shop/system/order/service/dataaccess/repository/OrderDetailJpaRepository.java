package com.online.shop.system.order.service.dataaccess.repository;

import com.online.shop.system.order.service.dataaccess.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailJpaRepository extends JpaRepository<OrderDetailEntity, String> {
}
