package com.online.shop.system.order.service.dataaccess.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Objects;


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
