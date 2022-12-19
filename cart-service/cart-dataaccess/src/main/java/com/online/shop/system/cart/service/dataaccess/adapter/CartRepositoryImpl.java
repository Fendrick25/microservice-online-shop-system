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
        cartJpaRepository.save(cartDataAccessMapper.emptyCartToCartEntity(cart));
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
            return cartDataAccessMapper.cartEntityToCart(cartJpaRepository.getReferenceById(cartEntity.getId()));
        }

        optionalCartItemEntity.get().setQuantity(optionalCartItemEntity.get().getQuantity() + cartItem.getQuantity());
        cartItemJpaRepository.save(optionalCartItemEntity.get());
        return cartDataAccessMapper.cartEntityToCart(cartJpaRepository.getReferenceById(cartEntity.getId()));
    }

    @Override
    @Transactional
    public CartItem updateCartItem(CartItem cartItem) {
        CartItemEntity cartItemEntity = findCartItem(cartItem.getId());
        cartItemEntity.setQuantity(cartItem.getQuantity());
        return cartDataAccessMapper.cartItemEntityToCartItem(cartItemJpaRepository.save(cartItemEntity));
    }

    @Override
    public void deleteCartItem(Integer cartItemID) {
        cartItemJpaRepository.deleteById(cartItemID);
    }

    @Override
    @Transactional
    public Cart getCart(UUID userID) {
        return cartDataAccessMapper.cartEntityToCart(cartJpaRepository.findByUserID(userID));
    }

    @Override
    @Transactional
    public void updateCart(Cart cart) {
        cart.getItems().forEach(this::updateCartItem);
    }

    @Override
    @Transactional
    public void emptyCart(UUID cartID) {
        cartItemJpaRepository.deleteAllByCartId(cartID);
    }

    private CartItemEntity findCartItem(Integer cartItemID){
        return cartItemJpaRepository.findById(cartItemID).orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));
    }


}
