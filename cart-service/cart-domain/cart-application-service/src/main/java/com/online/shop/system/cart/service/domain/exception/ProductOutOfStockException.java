package com.online.shop.system.cart.service.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductOutOfStockException extends RuntimeException{
    public ProductOutOfStockException(String message) {
        super(message);
    }
}
