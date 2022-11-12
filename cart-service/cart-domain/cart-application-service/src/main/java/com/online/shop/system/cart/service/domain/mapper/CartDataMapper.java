package com.online.shop.system.cart.service.domain.mapper;

import com.online.shop.system.cart.service.domain.dto.create.AddCartItem;
import com.online.shop.system.cart.service.domain.dto.create.UpdateCartItem;
import com.online.shop.system.cart.service.domain.dto.create.response.GetCartResponse;
import com.online.shop.system.cart.service.domain.dto.message.CheckProductStock;
import com.online.shop.system.cart.service.domain.dto.message.GetProductResponse;
import com.online.shop.system.cart.service.domain.entity.Cart;
import com.online.shop.system.cart.service.domain.entity.CartItem;
import com.online.shop.system.cart.service.domain.entity.Product;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartDataMapper {

    public CartItem addCartItemToCartItem(AddCartItem addCartItem){
        return CartItem.builder()
                .cartID(addCartItem.getCartID())
                .product(Product.builder()
                        .id(addCartItem.getProductID())
                        .build())
                .quantity(addCartItem.getQuantity())
                .build();
    }

    public CartItem updateCartItemToCartItem(UpdateCartItem updateCartItem){
        return CartItem.builder()
                .id(updateCartItem.getCartItemID())
                .product(Product.builder()
                        .id(updateCartItem.getProductID())
                        .build())
                .quantity(updateCartItem.getQuantity())
                .build();
    }

    public CheckProductStock addCartItemToCheckProductStock(AddCartItem addCartItem){
        return CheckProductStock.builder()
                .productID(addCartItem.getProductID())
                .quantity(addCartItem.getQuantity())
                .build();
    }

    public CheckProductStock updateCartItemToCheckProductStock(UpdateCartItem updateCartItem){
        return CheckProductStock.builder()
                .productID(updateCartItem.getProductID())
                .quantity(updateCartItem.getQuantity())
                .build();
    }

    public GetCartResponse cartToGetCartResponse(Cart cart){
        return GetCartResponse.builder()
                .cartID(cart.getId())
                .userID(cart.getUserID())
                .items(cart.getItems().stream()
                        .map(item -> GetCartResponse.Items.builder()
                                .cartItemID(item.getId())
                                .product(GetCartResponse.Items.Product.builder()
                                        .productID(item.getProduct().getId())
                                        .name(item.getProduct().getName())
                                        .description(item.getProduct().getDescription())
                                        .price(item.getProduct().getPrice())
                                        .imageUrl(item.getProduct().getImageUrl())
                                        .build())
                                .quantity(item.getQuantity())
                                .subTotal(item.getSubTotal())
                                .build()).collect(Collectors.toList()))
                .totalPrice(cart.getTotalPrice())
                .build();
    }

    public Product getProductResponseToProduct(GetProductResponse getProductResponse){
        return Product.builder()
                .id(getProductResponse.getProductID())
                .description(getProductResponse.getDescription())
                .name(getProductResponse.getName())
                .price(getProductResponse.getPrice())
                .imageUrl(getProductResponse.getImageUrl())
                .build();
    }
}
