package com.online.shop.system.product.service.domain;

import com.online.shop.system.product.service.domain.entity.ProductReview;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductReviewDomainServiceImpl implements ProductReviewDomainService {
    @Override
    public ProductReview validateProductReview(ProductReview productReview) {
        productReview.initializeProductReview();
        log.info("Product review for product id: {} initiated", productReview.getProductID());
        return productReview;
    }
}
