package com.online.shop.system.product.service.domain;

import com.online.shop.system.product.service.domain.dto.create.CreateProductReview;
import com.online.shop.system.product.service.domain.dto.create.UpdateProductReview;
import com.online.shop.system.product.service.domain.dto.create.response.GetProductReviewResponse;
import com.online.shop.system.product.service.domain.dto.create.response.PagingResponse;
import com.online.shop.system.product.service.domain.dto.create.response.ProductReviewResponse;
import com.online.shop.system.product.service.domain.entity.ProductReview;
import com.online.shop.system.product.service.domain.mapper.ProductReviewDataMapper;
import com.online.shop.system.product.service.domain.ports.input.service.ProductReviewApplicationService;
import com.online.shop.system.product.service.domain.ports.output.repository.ProductReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;


@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class ProductReviewApplicationServiceImpl implements ProductReviewApplicationService {

    private final ProductReviewRepository productReviewRepository;
    private final ProductReviewDataMapper productReviewDataMapper;
    private final ProductReviewDomainService productReviewDomainService;

    @Override
    public ProductReviewResponse createProductReview(CreateProductReview createProductReview) {
        ProductReview productReview = productReviewDomainService.validateProductReview(productReviewDataMapper.createProductReviewToProductReview(createProductReview));
        log.info("Product review created for product id: {}", productReview.getProductID());
        return productReviewDataMapper.productReviewToProductReviewResponse(productReviewRepository.createProductReview(productReview));
    }

    @Override
    public ProductReviewResponse updateProductReview(UpdateProductReview updateProductReview) {
        log.info("Product review updated for product id: {}", updateProductReview.getProductID());
        return productReviewDataMapper.productReviewToProductReviewResponse(productReviewRepository.updateProductReview(productReviewDataMapper.updateProductReviewToProductReview(updateProductReview)));
    }

    @Override
    public GetProductReviewResponse getProductReview(Integer productReviewID) {
        log.info("Product review found for id: {}", productReviewID);
        return productReviewDataMapper.productReviewToGetProductReviewResponse(productReviewRepository.getProductReview(productReviewID));
    }

    @Override
    public PagingResponse getProductReviewByProductID(UUID productID, int page, int size) {
        return productReviewRepository.getProductReviewByProductID(productID, page, size);
    }

    @Override
    public PagingResponse getProductReviewByProductIDAndRating(UUID productID, int rating, int page, int size) {
        return productReviewRepository.getProductReviewByProductIDAndRating(productID, rating, page, size);
    }


}
