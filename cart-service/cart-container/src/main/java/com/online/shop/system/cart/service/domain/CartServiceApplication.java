package com.online.shop.system.cart.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.online.shop.system.cart.service")
@EntityScan(basePackages = "com.online.shop.system.cart.service")
@SpringBootApplication(scanBasePackages = "com.online.shop.system")
public class CartServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartServiceApplication.class, args);
    }
}
