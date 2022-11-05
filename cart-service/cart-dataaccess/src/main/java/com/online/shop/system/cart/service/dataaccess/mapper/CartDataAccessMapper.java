package com.online.shop.system.cart.service.dataaccess.mapper;

import com.online.shop.system.cart.service.dataaccess.entity.CartEntity;
import com.online.shop.system.cart.service.dataaccess.entity.CartItemEntity;
import com.online.shop.system.cart.service.domain.entity.Cart;
import com.online.shop.system.cart.service.domain.entity.CartItem;
import com.online.shop.system.cart.service.domain.entity.Product;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartDataAccessMapper {

    public CartEntity cartToCartEntity(Cart cart){
        return CartEntity.builder()
                .id(cart.getId())
                .userID(cart.getUserID())
                .build();
    }

    public Cart cartEntityToCart(CartEntity cartEntity){
        return Cart.builder()
                .id(cartEntity.getId())
                .userID(cartEntity.getUserID())
                .items(cartEntity.getCartItems().stream().map(this::cartItemEntityToCartItem).collect(Collectors.toList()))
                .build();
    }

    private CartItem cartItemEntityToCartItem(CartItemEntity cartItemEntity){
        return CartItem.builder()
                .product(Product.builder()
                        .id(cartItemEntity.getProductID())
                        .build())
                .quantity(cartItemEntity.getQuantity())
                .build();
    }

    public CartItemEntity cartItemToCartItemEntity(CartItem cartItem){
        return CartItemEntity.builder()
                .productID(cartItem.getProduct().getId())
                .quantity(cartItem.getQuantity())
                .build();
    }
}
