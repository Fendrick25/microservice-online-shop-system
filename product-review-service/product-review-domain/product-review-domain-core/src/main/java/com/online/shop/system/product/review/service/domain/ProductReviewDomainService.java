package com.online.shop.system.product.review.service.domain;

import com.online.shop.system.product.review.service.domain.entity.ProductReview;

public interface ProductReviewDomainService {

    ProductReview validateProductReview(ProductReview productReview);
}
