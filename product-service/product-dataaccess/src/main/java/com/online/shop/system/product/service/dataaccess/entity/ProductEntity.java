package com.online.shop.system.product.service.dataaccess.entity;

import com.online.shop.system.product.service.domain.valueobject.ProductStatus;
import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@Entity
public class ProductEntity {

    @Id
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    private double rating;
    private int soldAmount;
    private String imageUrl;

    @ManyToOne()
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryEntity category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductRatingEntity> ratingLogs;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductReviewEntity> productReviews;
}
