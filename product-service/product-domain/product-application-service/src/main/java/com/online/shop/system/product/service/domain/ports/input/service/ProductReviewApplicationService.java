package com.online.shop.system.product.service.domain.ports.input.service;


import com.online.shop.system.product.service.domain.dto.create.CreateProductReview;
import com.online.shop.system.product.service.domain.dto.create.UpdateProductReview;
import com.online.shop.system.product.service.domain.dto.create.response.GetProductReviewResponse;
import com.online.shop.system.product.service.domain.dto.create.response.PagingResponse;
import com.online.shop.system.product.service.domain.dto.create.response.ProductReviewResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.UUID;

public interface ProductReviewApplicationService {
    ProductReviewResponse createProductReview(@Valid CreateProductReview createProductRating);
    ProductReviewResponse updateProductReview(@Valid UpdateProductReview updateProductReview);
    GetProductReviewResponse getProductReview(@Valid Integer productReviewID);
    PagingResponse getProductReviewByProductID(@Valid UUID productID, int page, int size);
    PagingResponse getProductReviewByProductIDAndRating(@Valid UUID productID, int rating, int page, int size);

}

