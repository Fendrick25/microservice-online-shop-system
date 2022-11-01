package com.online.shop.system.product.review.content.service.dataaccess.entity;

import com.online.shop.system.product.review.content.service.domain.entity.Content;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "products_review")
public class ProductReviewContentEntity {
    @Id
    private String id;
    private Integer productReviewID;
    private String productID;
    private List<Content> contents;
    @CreatedDate
    private LocalDateTime createdAt;

}
