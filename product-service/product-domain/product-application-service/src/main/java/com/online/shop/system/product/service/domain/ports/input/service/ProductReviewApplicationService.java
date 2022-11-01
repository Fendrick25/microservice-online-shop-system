package com.online.shop.system.product.service.domain.ports.input.service;


import com.online.shop.system.product.service.domain.dto.create.CreateProductReview;
import com.online.shop.system.product.service.domain.dto.create.UpdateProductReview;
import com.online.shop.system.product.service.domain.dto.create.response.GetProductReviewResponse;
import com.online.shop.system.product.service.domain.dto.create.response.PagingResponse;
import com.online.shop.system.product.service.domain.dto.create.response.ProductReviewResponse;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

public interface ProductReviewApplicationService {
    ProductReviewResponse createProductReview(@Validated CreateProductReview createProductRating);
    ProductReviewResponse updateProductReview(@Validated UpdateProductReview updateProductReview);
    GetProductReviewResponse getProductReview(@Validated Integer productReviewID);
    PagingResponse getProductReviewByProductID(@Validated UUID productID, int page, int size);
    PagingResponse getProductReviewByProductIDAndRating(@Validated UUID productID, int rating, int page, int size);

}

