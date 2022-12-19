package com.online.shop.system.payment.service.dataaccess.repository;

import com.online.shop.system.payment.service.dataaccess.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, UUID> {
}
