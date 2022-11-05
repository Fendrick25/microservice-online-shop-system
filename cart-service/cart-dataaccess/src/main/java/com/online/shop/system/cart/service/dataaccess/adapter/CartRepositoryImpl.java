package com.online.shop.system.cart.service.dataaccess.adapter;

import com.online.shop.system.cart.service.dataaccess.entity.CartEntity;
import com.online.shop.system.cart.service.dataaccess.entity.CartItemEntity;
import com.online.shop.system.cart.service.dataaccess.exception.ResourceNotFoundException;
import com.online.shop.system.cart.service.dataaccess.mapper.CartDataAccessMapper;
import com.online.shop.system.cart.service.dataaccess.repository.CartItemJpaRepository;
import com.online.shop.system.cart.service.dataaccess.repository.CartJpaRepository;
import com.online.shop.system.cart.service.domain.entity.Cart;
import com.online.shop.system.cart.service.domain.entity.CartItem;
import com.online.shop.system.cart.service.domain.ports.output.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepository {

    private final CartJpaRepository cartJpaRepository;
    private final CartDataAccessMapper cartDataAccessMapper;
    private final CartItemJpaRepository cartItemJpaRepository;

    @Override
    public void createCart(Cart cart) {
        cartJpaRepository.save(cartDataAccessMapper.cartToCartEntity(cart));
    }

    @Override
    @Transactional
    public Cart addCartItem(CartItem cartItem) {
        CartEntity cartEntity = cartJpaRepository.findById(cartItem.getCartID()).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        Optional<CartItemEntity> optionalCartItemEntity = cartEntity.getCartItems().stream().filter(itemEntity -> itemEntity.getProductID().equals(cartItem.getProduct().getId()))
                .findFirst();
        if(optionalCartItemEntity.isEmpty()){
            CartItemEntity cartItemEntity = cartDataAccessMapper.cartItemToCartItemEntity(cartItem);
            cartItemEntity.setCart(cartEntity);
            cartItemJpaRepository.save(cartItemEntity);
        }

        optionalCartItemEntity.get().setQuantity(optionalCartItemEntity.get().getQuantity() + cartItem.getQuantity());
        cartItemJpaRepository.save(optionalCartItemEntity.get());
        return cartDataAccessMapper.cartEntityToCart(cartJpaRepository.getReferenceById(cartEntity.getId()));
    }

    @Override
    public Cart updateCartItem(CartItem cartItem) {
        CartItemEntity cartItemEntity = cartItemJpaRepository.findById(cartItem.getId()).orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));
        cartItemEntity.setQuantity(cartItemEntity.getQuantity());
        cartItemJpaRepository.save(cartItemEntity);
        return cartDataAccessMapper.cartEntityToCart(cartJpaRepository.getReferenceById(cartItem.getCartID()));
    }

    @Override
    public void deleteCartItem(Integer cartItemID) {
        cartItemJpaRepository.deleteById(cartItemID);
    }

    @Override
    public Cart getCart(UUID userID) {
        return cartDataAccessMapper.cartEntityToCart(cartJpaRepository.findByUserID(userID));
    }


}
