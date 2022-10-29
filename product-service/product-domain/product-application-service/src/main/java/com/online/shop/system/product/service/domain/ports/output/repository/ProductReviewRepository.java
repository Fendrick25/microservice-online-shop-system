package com.online.shop.system.product.service.domain.ports.output.repository;


import com.online.shop.system.product.service.domain.dto.create.response.PagingResponse;
import com.online.shop.system.product.service.domain.dto.message.ProductReviewContentUrl;
import com.online.shop.system.product.service.domain.entity.ProductReview;

public interface ProductReviewRepository {

    ProductReview createProductReview(ProductReview productReview);
    ProductReview updateProductReview(ProductReview productReview);
    ProductReview getProductReview(Integer productReviewID);
    PagingResponse getProductReviewByProductID(String productID, int page, int size);
    PagingResponse getProductReviewByRating(int rating, int page, int size);

    void saveProductContentUrl(ProductReviewContentUrl productReviewContentUrl);
}
