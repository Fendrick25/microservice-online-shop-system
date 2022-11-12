package com.online.shop.system.cart.service.application.rest;

import com.online.shop.system.cart.service.domain.dto.create.AddCartItem;
import com.online.shop.system.cart.service.domain.dto.create.UpdateCartItem;
import com.online.shop.system.cart.service.domain.dto.create.response.CartIDResponse;
import com.online.shop.system.cart.service.domain.dto.create.response.Data;
import com.online.shop.system.cart.service.domain.dto.create.response.GetCartResponse;
import com.online.shop.system.cart.service.domain.ports.input.service.CartApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/carts")
public class CartController {

    private final CartApplicationService cartApplicationService;

    @PostMapping
    public ResponseEntity<Data<CartIDResponse>> addCartItem(@RequestBody AddCartItem addCartItem){
        return new ResponseEntity<>(new Data<>(cartApplicationService.addCartItem(addCartItem)), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Data<CartIDResponse>> updateCartItem(@RequestBody UpdateCartItem updateCartItem){
        return new ResponseEntity<>(new Data<>(cartApplicationService.updateCartItem(updateCartItem)), HttpStatus.OK);
    }

    @DeleteMapping("/cart-item/{cartItemID}")
    public ResponseEntity<?> deleteCartItem(@PathVariable("cartItemID") Integer cartItemID){
        cartApplicationService.deleteCartItem(cartItemID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userID}")
    public ResponseEntity<Data<GetCartResponse>> getCart(@PathVariable("userID") UUID userID){
        return new ResponseEntity<>(new Data<>(cartApplicationService.getCart(userID)), HttpStatus.OK);
    }
}
