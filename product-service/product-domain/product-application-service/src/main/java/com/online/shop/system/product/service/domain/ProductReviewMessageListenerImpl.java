package com.online.shop.system.product.service.domain;

import com.online.shop.system.product.service.domain.dto.message.ProductRating;
import com.online.shop.system.product.service.domain.ports.input.message.listener.ProductReviewMessageListener;
import com.online.shop.system.product.service.domain.ports.output.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductReviewMessageListenerImpl implements ProductReviewMessageListener {

    private final ProductRepository productRepository;
    @Override
    public void productReviewCreated(ProductRating productRating) {
        productRepository.saveProductRating(productRating);
        log.info("Product rating saved for product id: {}", productRating.getProductID());
    }
}
