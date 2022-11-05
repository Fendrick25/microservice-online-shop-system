package com.online.shop.system.cart.service.domain;

import com.online.shop.system.cart.service.domain.entity.Cart;

public interface CartDomainService {
    Cart initializeCart(Cart cart);
    Cart calculateCart(Cart cart);
}
