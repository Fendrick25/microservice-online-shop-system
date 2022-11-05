package com.online.shop.system.cart.service.domain.exception;

public class CartDomainException extends RuntimeException{
    public CartDomainException(String message) {
        super(message);
    }

    public CartDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
