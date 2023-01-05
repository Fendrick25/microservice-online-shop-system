package com.online.shop.system.order.service.dataaccess.entity;

import com.online.shop.system.order.service.domain.valueobject.OrderStatus;
import lombok.*;

import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetailEntity {

    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private ZonedDateTime createdAt;
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderEntity order;
}
