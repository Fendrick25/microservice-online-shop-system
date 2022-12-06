package com.online.shop.system.cart.service.domain.mapper;

import com.online.shop.system.cart.service.domain.dto.create.AddCartItem;
import com.online.shop.system.cart.service.domain.dto.create.UpdateCartItem;
import com.online.shop.system.cart.service.domain.dto.create.response.GetCartResponse;
import com.online.shop.system.cart.service.domain.dto.message.CartRequest;
import com.online.shop.system.cart.service.domain.dto.message.CheckProductStock;
import com.online.shop.system.cart.service.domain.dto.message.GetProductResponse;
import com.online.shop.system.cart.service.domain.entity.Cart;
import com.online.shop.system.cart.service.domain.entity.CartItem;
import com.online.shop.system.cart.service.domain.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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


    public List<CheckProductStock> cartItemsToCheckProductStock(Map<UUID, CartItem> cartItems){
        List<CheckProductStock> checkProductStocks = new ArrayList<>();
        cartItems.forEach((k, v) -> {
            checkProductStocks.add(CheckProductStock.builder()
                    .productID(v.getProduct().getId())
                    .quantity(v.getQuantity())
                    .build());
        });

        return checkProductStocks;
    }

    public CheckProductStock cartItemToCheckProductStock(CartItem cartItem){
        return CheckProductStock.builder()
                .productID(cartItem.getProduct().getId())
                .quantity(cartItem.getQuantity())
                .build();
    }



    public GetCartResponse cartToGetCartResponse(Cart cart){
        return GetCartResponse.builder()
                .cartID(cart.getId())
                .userID(cart.getUserID())
                .items(cart.getItems().stream()
                        .map(item -> GetCartResponse.Item.builder()
                                .cartItemID(item.getId())
                                .product(GetCartResponse.Item.Product.builder()
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

    public Cart getCartResponseToCart(GetCartResponse getCartResponse){
        return Cart.builder()
                .id(getCartResponse.getCartID())
                .userID(getCartResponse.getUserID())
                .items(getCartResponse.getItems().stream().map(this::getCartResponseItemToCartItem).collect(Collectors.toList()))
                .totalPrice(getCartResponse.getTotalPrice())
                .build();
    }

    private CartItem getCartResponseItemToCartItem(GetCartResponse.Item item){
        return CartItem.builder()
                .id(item.getCartItemID())
                .product(Product.builder()
                        .id(item.getProduct().getProductID())
                        .name(item.getProduct().getName())
                        .description(item.getProduct().getDescription())
                        .price(item.getProduct().getPrice())
                        .imageUrl(item.getProduct().getImageUrl())
                        .build())
                .quantity(item.getQuantity())
                .subTotal(item.getSubTotal())
                .build();
    }
}
