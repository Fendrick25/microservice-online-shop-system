package com.online.shop.system.product.review.content.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ProductReviewContentDomainService productReviewContentDomainService(){
        return new ProductReviewContentDomainServiceImpl();
    }
}
