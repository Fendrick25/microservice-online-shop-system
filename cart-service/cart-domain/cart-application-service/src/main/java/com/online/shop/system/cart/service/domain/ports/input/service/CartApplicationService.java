package com.online.shop.system.cart.service.domain.ports.input.service;


import com.online.shop.system.cart.service.domain.dto.create.AddCartItem;
import com.online.shop.system.cart.service.domain.dto.create.UpdateCartItem;
import com.online.shop.system.cart.service.domain.dto.create.response.CartIDResponse;
import com.online.shop.system.cart.service.domain.dto.create.response.GetCartResponse;
import com.online.shop.system.cart.service.domain.dto.message.User;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

public interface CartApplicationService {
    void createCart(User user);
    CartIDResponse addCartItem(@Validated AddCartItem addCartItem);
    CartIDResponse updateCartItem(@Validated UpdateCartItem updateCartItem);
    void emptyCart(UUID cartID);
    void deleteCartItem(Integer cartItemID);
    GetCartResponse getCart(UUID userID);
}
