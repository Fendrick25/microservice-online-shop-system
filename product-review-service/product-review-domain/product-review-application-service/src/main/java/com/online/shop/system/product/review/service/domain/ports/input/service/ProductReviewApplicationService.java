package com.online.shop.system.product.review.service.domain.ports.input.service;

import com.online.shop.system.product.review.service.domain.dto.create.CreateProductReview;
import com.online.shop.system.product.review.service.domain.dto.create.UpdateProductReview;
import com.online.shop.system.product.review.service.domain.dto.create.response.GetProductReviewResponse;
import com.online.shop.system.product.review.service.domain.dto.create.response.PagingResponse;
import com.online.shop.system.product.review.service.domain.dto.create.response.ProductReviewResponse;

public interface ProductReviewApplicationService {
    ProductReviewResponse createProductReview(CreateProductReview createProductRating);
    ProductReviewResponse updateProductReview(UpdateProductReview updateProductReview);
    GetProductReviewResponse getProductReview(Integer productReviewID);
    PagingResponse getProductReviewByProductID(String productID, int page, int size);
    PagingResponse getProductReviewByRating(int rating, int page, int size);

}

