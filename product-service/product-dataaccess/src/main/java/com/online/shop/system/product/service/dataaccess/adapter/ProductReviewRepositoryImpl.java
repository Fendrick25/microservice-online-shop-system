package com.online.shop.system.product.service.dataaccess.adapter;

import com.online.shop.system.product.service.dataaccess.entity.ProductEntity;
import com.online.shop.system.product.service.dataaccess.entity.ProductReviewEntity;
import com.online.shop.system.product.service.dataaccess.exception.ResourceNotFoundException;
import com.online.shop.system.product.service.dataaccess.mapper.ProductReviewDataAccessMapper;
import com.online.shop.system.product.service.dataaccess.repository.ProductJpaRepository;
import com.online.shop.system.product.service.dataaccess.repository.ProductReviewJpaRepository;
import com.online.shop.system.product.service.domain.dto.create.response.Data;
import com.online.shop.system.product.service.domain.dto.create.response.PagingResponse;
import com.online.shop.system.product.service.domain.dto.message.ProductReviewContentUrl;
import com.online.shop.system.product.service.domain.entity.ProductReview;
import com.online.shop.system.product.service.domain.ports.output.repository.ProductReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductReviewRepositoryImpl implements ProductReviewRepository {

    private ProductReviewJpaRepository productReviewJpaRepository;
    private ProductJpaRepository productJpaRepository;
    private ProductReviewDataAccessMapper productReviewDataAccessMapper;

    @Override
    public ProductReview createProductReview(ProductReview productReview) {
        ProductReviewEntity productReviewEntity = productReviewDataAccessMapper.productReviewToProductReviewEntity(productReview);
        ProductEntity productEntity = productJpaRepository.findById(productReview.getProductID()).orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
        productReviewEntity.setProduct(productEntity);
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
    public PagingResponse getProductReviewByProductID(String productID, int page, int size) {
        Pageable paging = PageRequest.of(page - 1, size);
        Page<ProductReviewEntity> productReviews = productReviewJpaRepository.findByProductId(productID, paging);
        return PagingResponse.builder()
                .data(new Data<>(productReviews.getContent().stream().map(productReviewDataAccessMapper::productReviewEntityToProductReview).collect(Collectors.toList())))
                .currentPage(productReviews.getNumber() + 1)
                .size(productReviews.getSize())
                .total(((int) productReviews.getTotalElements()))
                .totalPages(productReviews.getTotalPages())
                .build();
    }

    @Override
    public PagingResponse getProductReviewByRating(int rating, int page, int size) {
        Pageable paging = PageRequest.of(page - 1, size);
        Page<ProductReviewEntity> productReviews = productReviewJpaRepository.findByRating(rating, paging);
        return PagingResponse.builder()
                .data(new Data<>(productReviews.getContent().stream().map(productReviewDataAccessMapper::productReviewEntityToProductReview).collect(Collectors.toList())))
                .currentPage(productReviews.getNumber() + 1)
                .size(productReviews.getSize())
                .total(((int) productReviews.getTotalElements()))
                .totalPages(productReviews.getTotalPages())
                .build();
    }

    @Override
    public void saveProductContentUrl(ProductReviewContentUrl productReviewContentUrl) {
        productJpaRepository.findById(productReviewContentUrl.getProductID()).orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
        ProductReviewEntity productReviewEntity = productReviewJpaRepository.findByProductId(productReviewContentUrl.getProductID());
        productReviewEntity.setImageUrls(productReviewEntity.getImageUrls());
        productReviewEntity.setVideoUrls(productReviewEntity.getVideoUrls());
    }
}
