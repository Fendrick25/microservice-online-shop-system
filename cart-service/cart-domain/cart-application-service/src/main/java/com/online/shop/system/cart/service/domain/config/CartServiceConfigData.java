package com.online.shop.system.cart.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "cart-service")
public class CartServiceConfigData {
    private String cartTopicName;
}
