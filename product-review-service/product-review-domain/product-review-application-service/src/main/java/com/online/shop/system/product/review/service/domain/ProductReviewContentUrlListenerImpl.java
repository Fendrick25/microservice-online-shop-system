package com.online.shop.system.product.review.service.domain;

import com.online.shop.system.product.review.service.domain.dto.message.ProductReviewContentUrl;
import com.online.shop.system.product.review.service.domain.ports.input.message.listener.ProductReviewContentUrlListener;
import com.online.shop.system.product.review.service.domain.ports.output.repository.ProductReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductReviewContentUrlListenerImpl implements ProductReviewContentUrlListener {

    private final ProductReviewRepository productReviewRepository;

    @Override
    public void productReviewContentUrlCreated(ProductReviewContentUrl productReviewContentUrl) {
        productReviewRepository.saveProductContentUrl(productReviewContentUrl);
        log.info("Product review content url saved for product id: {}", productReviewContentUrl.getProductID());
    }
}
