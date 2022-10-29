package com.online.shop.system.product.review.service.domain.exception;

public class ProductReviewDomainException extends RuntimeException{
    public ProductReviewDomainException(String message) {
        super(message);
    }

    public ProductReviewDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
