package com.online.shop.system.cart.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CartDomainService cartDomainService(){
        return new CartDomainServiceImpl();
    }
}
