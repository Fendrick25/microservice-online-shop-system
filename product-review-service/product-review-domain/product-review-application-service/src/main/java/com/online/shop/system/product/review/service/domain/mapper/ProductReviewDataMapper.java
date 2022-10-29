package com.online.shop.system.product.review.service.domain.mapper;

import com.online.shop.system.product.review.service.domain.dto.create.CreateProductReview;
import com.online.shop.system.product.review.service.domain.dto.create.UpdateProductReview;
import com.online.shop.system.product.review.service.domain.dto.create.response.GetProductReviewResponse;
import com.online.shop.system.product.review.service.domain.dto.create.response.ProductReviewResponse;
import com.online.shop.system.product.review.service.domain.dto.message.ProductRating;
import com.online.shop.system.product.review.service.domain.entity.ProductReview;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductReviewDataMapper {

    public ProductReview createProductReviewToProductReview(CreateProductReview createProductReview){
        return ProductReview.builder()
                .productID(createProductReview.getProductID())
                .description(createProductReview.getDescription())
                .rating(createProductReview.getRating())
                .userID(createProductReview.getUserID())
                .username(createProductReview.getUsername())
                .build();
    }

    public ProductReviewResponse productReviewToProductReviewResponse(ProductReview productReview){
        return ProductReviewResponse.builder()
                .productReviewID(productReview.getProductReviewID())
                .productID(UUID.fromString(productReview.getProductID()))
                .build();
    }

    public ProductRating productReviewToProductRating(ProductReview productReview){
        return ProductRating.builder()
                .productID(UUID.fromString(productReview.getProductID()))
                .rating(productReview.getRating())
                .build();
    }

    public ProductReview updateProductReviewToProductReview(UpdateProductReview updateProductReview){
        return ProductReview.builder()
                .productReviewID(updateProductReview.getProductReviewID())
                .productID(updateProductReview.getProductID())
                .description(updateProductReview.getDescription())
                .build();
    }

    public GetProductReviewResponse productReviewToGetProductReviewResponse(ProductReview productReview){
        return GetProductReviewResponse.builder()
                .productReviewID(productReview.getProductReviewID())
                .productID(productReview.getProductID())
                .description(productReview.getDescription())
                .rating(productReview.getRating())
                .userID(productReview.getUserID())
                .username(productReview.getUsername())
                .imageUrls(productReview.getImageUrls())
                .videoUrls(productReview.getVideoUrls())
                .createdAt(productReview.getCreatedAt())
                .build();
    }
}
