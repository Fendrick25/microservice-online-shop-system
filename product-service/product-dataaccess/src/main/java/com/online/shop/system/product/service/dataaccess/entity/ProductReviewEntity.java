package com.online.shop.system.product.service.dataaccess.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_reviews")
@Entity
public class ProductReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer productReviewID;
    private UUID userID;
    private String username;
    private int rating;
    private String description;
    private String imageUrl;
    private String videoUrl;
    private ZonedDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity product;

}
