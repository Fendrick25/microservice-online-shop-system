package com.online.shop.system.order.service.dataaccess.entity;

import com.online.shop.system.order.service.domain.valueobject.OrderAddress;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class OrderEntity {

    @Id
    private UUID id;
    private BigDecimal price;
    private ZonedDateTime purchaseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<OrderItemEntity> orderItems;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private OrderAddress orderAddress;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetails;





}
