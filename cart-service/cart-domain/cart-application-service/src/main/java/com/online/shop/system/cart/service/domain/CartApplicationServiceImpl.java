package com.online.shop.system.cart.service.domain;

import com.online.shop.system.cart.service.domain.dto.create.AddCartItem;
import com.online.shop.system.cart.service.domain.dto.create.UpdateCartItem;
import com.online.shop.system.cart.service.domain.dto.create.response.CartIDResponse;
import com.online.shop.system.cart.service.domain.dto.create.response.GetCartResponse;
import com.online.shop.system.cart.service.domain.dto.message.CheckProductStock;
import com.online.shop.system.cart.service.domain.dto.message.CheckProductStockResponse;
import com.online.shop.system.cart.service.domain.dto.message.GetProductResponse;
import com.online.shop.system.cart.service.domain.dto.message.User;
import com.online.shop.system.cart.service.domain.entity.Cart;
import com.online.shop.system.cart.service.domain.entity.CartItem;
import com.online.shop.system.cart.service.domain.entity.Product;
import com.online.shop.system.cart.service.domain.exception.ProductOutOfStockException;
import com.online.shop.system.cart.service.domain.mapper.CartDataMapper;
import com.online.shop.system.cart.service.domain.ports.input.service.CartApplicationService;
import com.online.shop.system.cart.service.domain.ports.output.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Validated
@Service
@RequiredArgsConstructor
public class CartApplicationServiceImpl implements CartApplicationService {

    private final CartDomainService cartDomainService;
    private final CartRepository cartRepository;

    private final CartDataMapper cartDataMapper;

    private final WebClient.Builder webClient;

    @Override
    public void createCart(User user) {
        Cart cart = cartDomainService.initializeCart(Cart.builder()
                        .userID(user.getUserID())
                .build());

        cartRepository.createCart(cart);
        log.info("Cart created for user id: {}", cart.getUserID());
    }

    @Override
    public CartIDResponse addCartItem(AddCartItem addCartItem) {
        CartItem cartItem = cartDataMapper.addCartItemToCartItem(addCartItem);
        checkOneProductStock(cartItem);
        Cart cart = cartRepository.addCartItem(cartItem);
        return CartIDResponse.builder()
                .cartID(cart.getId())
                .build();
    }

    @Override
    public CartIDResponse updateCartItem(UpdateCartItem updateCartItem) {
        CartItem cartItem = cartDataMapper.updateCartItemToCartItem(updateCartItem);
        checkOneProductStock(cartItem);
        CartItem updatedCartItem = cartRepository.updateCartItem(cartItem);
        log.info("Product with id: {} updated, cart id: {}", updateCartItem.getProductID(), updatedCartItem.getId());
        return CartIDResponse.builder()
                .cartID(updatedCartItem.getCartID())
                .build();
    }

    @Override
    public void emptyCart(UUID cartID) {
        cartRepository.emptyCart(cartID);
        log.info("Cart item deleted for cart id: {}", cartID);
    }

    @Override
    public void deleteCartItem(Integer cartItemID) {
        cartRepository.deleteCartItem(cartItemID);
        log.info("Cart item with id: {} deleted", cartItemID);
    }

    @Override
    public GetCartResponse getCart(UUID userID) {
        Cart cart = cartRepository.getCart(userID);
        Map<UUID, CartItem> cartItemMap = new HashMap<>();
        cart.getItems().forEach(cartItem -> cartItemMap.put(cartItem.getProduct().getId(), cartItem));
        cart.setItems(checkManyProductStock(cartItemMap));

        Cart updatedCart = cartDomainService.calculateCart(updateCartInformation(cart));
        log.info("Cart found for user id: {}", updatedCart.getUserID());
        cartRepository.updateCart(cart);
        return cartDataMapper.cartToGetCartResponse(updatedCart);

    }

    private List<CheckProductStockResponse> getProductStock(List<CheckProductStock> checkProductStocks){
        Mono<List<CheckProductStockResponse>> response = webClient.build().method(HttpMethod.GET)
                .uri("http://product-service/api/v1/products/stocks/")
                .body(BodyInserters.fromValue(checkProductStocks))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});

        return response.block();
    }

    private void checkOneProductStock(CartItem cartItem){
        List<CheckProductStockResponse> checkedProducts = getProductStock(List.of(cartDataMapper.cartItemToCheckProductStock(cartItem)));
        assert checkedProducts != null;
        if (checkedProducts.size() == 1 && !(checkedProducts.get(0).getMessage().equalsIgnoreCase("IN_STOCK")))
            throw new ProductOutOfStockException("Stock left: " + checkedProducts.get(0).getMessage());
    }

    private List<CartItem> checkManyProductStock(Map<UUID, CartItem> cartItems){
        List<CartItem> availableItems = new ArrayList<>();
        List<CheckProductStockResponse> checkedProducts = getProductStock(cartDataMapper.cartItemsToCheckProductStock(cartItems));
        checkedProducts.forEach(checkedProduct -> {
            if(!checkedProduct.getMessage().equalsIgnoreCase("IN_STOCK")){
                CartItem cartItem = cartItems.get(checkedProduct.getProductID());
                cartItem.setQuantity(Integer.parseInt(checkedProduct.getMessage()));
                availableItems.add(cartItem);
            }else{
                availableItems.add(cartItems.get(checkedProduct.getProductID()));
            }
        });
        return availableItems;
    }

    private Cart updateCartInformation(Cart cart){
        Map<UUID, Product> productMap = new HashMap<>();
        Mono<List<GetProductResponse>> response = webClient.build().method(HttpMethod.GET)
                .uri("http://product-service/api/v1/products/carts/info")
                .body(BodyInserters.fromValue(cart.getItems().stream()
                        .map(cartItem -> cartItem.getProduct().getId()).collect(Collectors.toList())))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});

        List<GetProductResponse> products = response.block();
        if (products != null) {
            products.forEach(product -> productMap.put(product.getProductID(), cartDataMapper.getProductResponseToProduct(product)));
        }
        cart.getItems().forEach(cartItem -> cartItem.setProduct(productMap.get(cartItem.getProduct().getId())));
        return cart;
    }

}
