package com.online.shop.system.product.review.content.service.domain.exception;

public class ProductReviewContentDomainException extends RuntimeException{
    public ProductReviewContentDomainException(String message) {
        super(message);
    }

    public ProductReviewContentDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
