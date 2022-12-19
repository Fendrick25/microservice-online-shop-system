package com.online.shop.system.payment.service.dataaccess.entity;

import com.online.shop.system.payment.service.domain.valueobject.PaymentStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payments")
public class PaymentEntity {

    @Id
    private UUID id;
    private UUID orderID;
    private BigDecimal price;
    private ZonedDateTime purchasedDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne(fetch =  FetchType.LAZY)
    private UserEntity user;
}
