package com.online.shop.system.cart.service.domain.ports.input.service;


import com.online.shop.system.cart.service.domain.dto.create.AddCartItem;
import com.online.shop.system.cart.service.domain.dto.create.UpdateCartItem;
import com.online.shop.system.cart.service.domain.dto.create.response.CartIDResponse;
import com.online.shop.system.cart.service.domain.dto.create.response.GetCartResponse;
import com.online.shop.system.cart.service.domain.dto.message.CartOrderResponse;
import com.online.shop.system.cart.service.domain.dto.message.CartRequest;
import com.online.shop.system.cart.service.domain.dto.message.User;

import javax.validation.Valid;
import java.util.UUID;

public interface CartApplicationService {
    void createCart(User user);
    CartIDResponse addCartItem(@Valid AddCartItem addCartItem);
    CartIDResponse updateCartItem(@Valid UpdateCartItem updateCartItem);
    void emptyCart(UUID cartID);
    void deleteCartItem(Integer cartItemID);
    GetCartResponse getCart(UUID userID);
    CartOrderResponse requestCart(CartRequest cartRequest);
}
