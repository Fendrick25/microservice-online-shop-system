package com.online.shop.system.product.review.content.service.dataaccess.adapter;

import com.online.shop.system.product.review.content.service.dataaccess.mapper.ProductReviewContentDataAccessMapper;
import com.online.shop.system.product.review.content.service.dataaccess.repository.ProductReviewContentMongoRepository;
import com.online.shop.system.product.review.content.service.domain.entity.ProductReviewContent;
import com.online.shop.system.product.review.content.service.domain.ports.output.repository.ProductReviewContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ProductReviewContentRepositoryImpl implements ProductReviewContentRepository {

    private final ProductReviewContentMongoRepository productReviewContentMongoRepository;
    private final ProductReviewContentDataAccessMapper productReviewContentDataAccessMapper;
    @Override
    @Transactional
    public String uploadContent(ProductReviewContent productReviewContent) {
         return productReviewContentMongoRepository.save(productReviewContentDataAccessMapper.productReviewContentToProductReviewContentEntity(productReviewContent)).getId();
    }
}
