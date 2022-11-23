package com.online.shop.system.product.service.dataaccess.adapter;

import com.online.shop.system.product.service.dataaccess.entity.ProductEntity;
import com.online.shop.system.product.service.dataaccess.entity.ProductRatingEntity;
import com.online.shop.system.product.service.dataaccess.entity.ProductReviewEntity;
import com.online.shop.system.product.service.dataaccess.exception.ResourceNotFoundException;
import com.online.shop.system.product.service.dataaccess.mapper.ProductReviewDataAccessMapper;
import com.online.shop.system.product.service.dataaccess.repository.ProductJpaRepository;
import com.online.shop.system.product.service.dataaccess.repository.ProductRatingJpaRepository;
import com.online.shop.system.product.service.dataaccess.repository.ProductReviewJpaRepository;
import com.online.shop.system.product.service.domain.dto.create.response.Data;
import com.online.shop.system.product.service.domain.dto.create.response.PagingResponse;
import com.online.shop.system.product.service.domain.dto.message.ProductReviewContentUrl;
import com.online.shop.system.product.service.domain.entity.ProductReview;
import com.online.shop.system.product.service.domain.ports.output.repository.ProductReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductReviewRepositoryImpl implements ProductReviewRepository {

    private final ProductReviewJpaRepository productReviewJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    private final ProductRatingJpaRepository productRatingJpaRepository;
    private final ProductReviewDataAccessMapper productReviewDataAccessMapper;

    @Override
    @Transactional
    public ProductReview createProductReview(ProductReview productReview) {
        ProductReviewEntity productReviewEntity = productReviewDataAccessMapper.productReviewToProductReviewEntity(productReview);
        ProductEntity productEntity = productJpaRepository.findById(productReview.getProductID()).orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
        productReviewEntity.setProduct(productEntity);
        productRatingJpaRepository.save(ProductRatingEntity.builder()
                        .rating(productReview.getRating())
                        .product(productEntity)
                .build());
        return productReviewDataAccessMapper.productReviewEntityToProductReview(productReviewJpaRepository.save(productReviewEntity));
    }

    @Override
    public ProductReview updateProductReview(ProductReview productReview) {
        productJpaRepository.findById(productReview.getProductID()).orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
        ProductReviewEntity productReviewEntity = productReviewJpaRepository.findById(productReview.getProductReviewID()).orElseThrow(() -> new ResourceNotFoundException("Product review not found!"));
        productReviewEntity.setDescription(productReview.getDescription());
        return productReviewDataAccessMapper.productReviewEntityToProductReview(productReviewJpaRepository.save(productReviewEntity));
    }

    @Override
    public ProductReview getProductReview(Integer productReviewID) {
        return productReviewDataAccessMapper.productReviewEntityToProductReview(productReviewJpaRepository.findById(productReviewID).orElseThrow(() -> new ResourceNotFoundException("Product review not found!")));
    }

    @Override
    public PagingResponse getProductReviewByProductID(UUID productID, int page, int size) {
        Pageable paging = PageRequest.of(page - 1, size);
        Page<ProductReviewEntity> productReviews = productReviewJpaRepository.findByProductId(productID, paging);
        return PagingResponse.builder()
                .data(productReviews.getContent().stream().map(productReviewDataAccessMapper::productReviewEntityToProductReview).collect(Collectors.toList()))
                .currentPage(productReviews.getNumber() + 1)
                .size(productReviews.getSize())
                .total(((int) productReviews.getTotalElements()))
                .totalPages(productReviews.getTotalPages())
                .build();
    }

    @Override
    public PagingResponse getProductReviewByProductIDAndRating(UUID productID, int rating, int page, int size) {
        Pageable paging = PageRequest.of(page - 1, size);
        Page<ProductReviewEntity> productReviews = productReviewJpaRepository.findByProductIdAndRating(productID, rating, paging);
        return PagingResponse.builder()
                .data(productReviews.getContent().stream().map(productReviewDataAccessMapper::productReviewEntityToProductReview).collect(Collectors.toList()))
                .currentPage(productReviews.getNumber() + 1)
                .size(productReviews.getSize())
                .total(((int) productReviews.getTotalElements()))
                .totalPages(productReviews.getTotalPages())
                .build();
    }

    @Override
    @Transactional
    public void saveProductContentUrl(ProductReviewContentUrl productReviewContentUrl) {
        log.info(productReviewContentUrl.getProductID().toString());
        productJpaRepository.findById(productReviewContentUrl.getProductID()).orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
        ProductReviewEntity productReviewEntity = productReviewJpaRepository.findById(productReviewContentUrl.getProductReviewID()).orElseThrow(() -> new ResourceNotFoundException("Product review not found!"));
        log.info(productReviewEntity.getProductReviewID().toString());
        productReviewEntity.setImageUrl(productReviewContentUrl.getImageUrl());
        productReviewEntity.setVideoUrl(productReviewContentUrl.getVideoUrl());
        productReviewJpaRepository.save(productReviewEntity);
    }
}
