package com.online.shop.system.product.review.content.service.domain.ports.output.repository;

import com.online.shop.system.product.review.content.service.domain.entity.ProductReviewContent;
public interface ProductReviewContentRepository {

    String uploadContent(ProductReviewContent productReviewContent);
}
