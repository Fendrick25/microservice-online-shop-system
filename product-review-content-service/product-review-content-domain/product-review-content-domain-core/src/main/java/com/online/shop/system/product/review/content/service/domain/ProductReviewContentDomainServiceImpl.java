package com.online.shop.system.product.review.content.service.domain;

import com.online.shop.system.product.review.content.service.domain.entity.ProductReviewContent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductReviewContentDomainServiceImpl implements ProductReviewContentDomainService{
    @Override
    public ProductReviewContent validateProductReviewContent(ProductReviewContent productReviewContent) {
        productReviewContent.initializeProductReviewContent();
        log.info("Product review content for product id: {} initialized", productReviewContent.getProductID());
        return productReviewContent;
    }
}
