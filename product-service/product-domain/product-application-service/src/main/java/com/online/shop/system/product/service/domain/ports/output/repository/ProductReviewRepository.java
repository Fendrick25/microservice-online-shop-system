package com.online.shop.system.product.service.domain.ports.output.repository;


import com.online.shop.system.product.service.domain.dto.create.response.PagingResponse;
import com.online.shop.system.product.service.domain.dto.message.ProductReviewContentUrl;
import com.online.shop.system.product.service.domain.entity.ProductReview;

import java.util.UUID;

public interface ProductReviewRepository {

    ProductReview createProductReview(ProductReview productReview);
    ProductReview updateProductReview(ProductReview productReview);
    ProductReview getProductReview(Integer productReviewID);
    PagingResponse getProductReviewByProductID(UUID productID, int page, int size);
    PagingResponse getProductReviewByProductIDAndRating(UUID productID, int rating, int page, int size);

    void saveProductContentUrl(ProductReviewContentUrl productReviewContentUrl);
}
