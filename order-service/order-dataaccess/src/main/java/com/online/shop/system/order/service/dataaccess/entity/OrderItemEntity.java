package com.online.shop.system.order.service.dataaccess.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import java.math.BigDecimal;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class OrderItemEntity {

    private Integer id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsobb")
    private ProductEntity product;
    private int quantity;
    private BigDecimal subTotal;
}
