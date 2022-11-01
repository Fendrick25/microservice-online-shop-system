package com.online.shop.system.product.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ProductDomainService productDomainService(){
        return new ProductDomainServiceImpl();
    }

    @Bean
    public CategoryDomainService categoryDomainService(){
        return new CategoryDomainServiceImpl();
    }

    @Bean
    public ProductReviewDomainService productReviewDomainService(){
        return new ProductReviewDomainServiceImpl();
    }
}
