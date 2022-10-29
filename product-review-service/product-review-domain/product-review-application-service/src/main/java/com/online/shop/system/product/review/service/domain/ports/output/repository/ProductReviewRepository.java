package com.online.shop.system.product.review.service.domain.ports.output.repository;

import com.online.shop.system.product.review.service.domain.dto.create.response.PagingResponse;
import com.online.shop.system.product.review.service.domain.dto.message.ProductReviewContentUrl;
import com.online.shop.system.product.review.service.domain.entity.ProductReview;

import java.util.List;
import java.util.UUID;

public interface ProductReviewRepository {

    ProductReview createProductReview(ProductReview productReview);
    ProductReview updateProductReview(ProductReview productReview);
    ProductReview getProductReview(Integer productReviewID);
    PagingResponse getProductReviewByProductID(String productID, int page, int size);
    PagingResponse getProductReviewByRating(int rating, int page, int size);

    void saveProductContentUrl(ProductReviewContentUrl productReviewContentUrl);
}
