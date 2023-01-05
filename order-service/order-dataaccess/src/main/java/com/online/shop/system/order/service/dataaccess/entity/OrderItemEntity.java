package com.online.shop.system.order.service.dataaccess.entity;

import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.Column;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.Objects;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEntity {

    private Integer id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsobb")
    private ProductEntity product;
    private int quantity;
    private BigDecimal subTotal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemEntity that = (OrderItemEntity) o;
        return id.equals(that.id) && product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product);
    }
}
