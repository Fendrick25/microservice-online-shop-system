package com.online.shop.system.cart.service.domain;

import com.online.shop.system.cart.service.domain.entity.Cart;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CartDomainServiceImpl implements CartDomainService{
    @Override
    public Cart initializeCart(Cart cart) {
        cart.initializeCart();
        log.info("Cart with id: {} initialized", cart.getId());
        return cart;
    }

    @Override
    public Cart calculateCart(Cart cart) {
        cart.calculateTotalPrice();
        log.info("Cart with id: {} calculated", cart.getId());
        return cart;
    }
}
