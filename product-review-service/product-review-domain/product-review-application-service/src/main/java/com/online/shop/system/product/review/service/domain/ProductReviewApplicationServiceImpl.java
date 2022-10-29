package com.online.shop.system.product.review.service.domain;

import com.online.shop.system.product.review.service.domain.dto.create.CreateProductReview;
import com.online.shop.system.product.review.service.domain.dto.create.UpdateProductReview;
import com.online.shop.system.product.review.service.domain.dto.create.response.GetProductReviewResponse;
import com.online.shop.system.product.review.service.domain.dto.create.response.PagingResponse;
import com.online.shop.system.product.review.service.domain.dto.create.response.ProductReviewResponse;
import com.online.shop.system.product.review.service.domain.entity.ProductReview;
import com.online.shop.system.product.review.service.domain.mapper.ProductReviewDataMapper;
import com.online.shop.system.product.review.service.domain.ports.input.service.ProductReviewApplicationService;
import com.online.shop.system.product.review.service.domain.ports.output.message.publisher.ProductRatingMessagePublisher;
import com.online.shop.system.product.review.service.domain.ports.output.repository.ProductReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class ProductReviewApplicationServiceImpl implements ProductReviewApplicationService {

    private final ProductRatingMessagePublisher productRatingMessagePublisher;
    private final ProductReviewRepository productReviewRepository;
    private final ProductReviewDataMapper productReviewDataMapper;
    private final ProductReviewDomainService productReviewDomainService;

    @Override
    public ProductReviewResponse createProductReview(CreateProductReview createProductReview) {
        ProductReview productReview = productReviewDomainService.validateProductReview(productReviewDataMapper.createProductReviewToProductReview(createProductReview));
        productRatingMessagePublisher.publish(productReviewDataMapper.productReviewToProductRating(productReview));
        return productReviewDataMapper.productReviewToProductReviewResponse(productReviewRepository.createProductReview(productReview));
    }

    @Override
    public ProductReviewResponse updateProductReview(UpdateProductReview updateProductReview) {
        return productReviewDataMapper.productReviewToProductReviewResponse(productReviewRepository.updateProductReview(productReviewDataMapper.updateProductReviewToProductReview(updateProductReview)));
    }

    @Override
    public GetProductReviewResponse getProductReview(Integer productReviewID) {
        return productReviewDataMapper.productReviewToGetProductReviewResponse(productReviewRepository.getProductReview(productReviewID));
    }

    @Override
    public PagingResponse getProductReviewByProductID(String productID, int page, int size) {
        return productReviewRepository.getProductReviewByProductID(productID, page, size);
    }

    @Override
    public PagingResponse getProductReviewByRating(int rating, int page, int size) {
        return productReviewRepository.getProductReviewByRating(rating, page, size);
    }


}
