package com.online.shop.system.cart.service.domain.ports.output.repository;

import com.online.shop.system.cart.service.domain.entity.Cart;
import com.online.shop.system.cart.service.domain.entity.CartItem;

import java.util.UUID;

public interface CartRepository {

    void createCart(Cart cart);
    Cart addCartItem(CartItem cartItem);
    Cart updateCartItem(CartItem cartItem);
    void deleteCartItem(Integer cartItemID);
    Cart getCart(UUID userID);
}
