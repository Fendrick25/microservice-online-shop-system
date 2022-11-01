package com.online.shop.system.product.review.content.service.dataaccess.exception;

import com.online.shop.system.product.review.content.service.domain.api.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ProductReviewContentDataAccessException {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
        List<String> details = new ArrayList<>();
        details.add(exception.getMessage());

        ApiError err = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND)
                .message("Data not found")
                .errors(details)
                .build();

        return new ResponseEntity<>(err, err.getStatus());
    }
}
