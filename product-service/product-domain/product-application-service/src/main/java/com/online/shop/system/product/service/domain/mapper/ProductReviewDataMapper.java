package com.online.shop.system.product.service.domain.mapper;

import com.online.shop.system.product.service.domain.dto.create.CreateProductReview;
import com.online.shop.system.product.service.domain.dto.create.UpdateProductReview;
import com.online.shop.system.product.service.domain.dto.create.response.GetProductReviewResponse;
import com.online.shop.system.product.service.domain.dto.create.response.ProductReviewResponse;
import com.online.shop.system.product.service.domain.entity.ProductReview;
import org.springframework.stereotype.Component;

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
                .productID(productReview.getProductID())
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
                .imageUrl(productReview.getImageUrl())
                .videoUrl(productReview.getVideoUrl())
                .createdAt(productReview.getCreatedAt())
                .build();
    }
}
