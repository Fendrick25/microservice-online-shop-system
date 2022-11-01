package com.online.shop.system.product.service.application.rest;

import com.online.shop.system.product.service.domain.dto.create.CreateProductReview;
import com.online.shop.system.product.service.domain.dto.create.UpdateProductReview;
import com.online.shop.system.product.service.domain.dto.create.response.Data;
import com.online.shop.system.product.service.domain.dto.create.response.GetProductReviewResponse;
import com.online.shop.system.product.service.domain.dto.create.response.PagingResponse;
import com.online.shop.system.product.service.domain.dto.create.response.ProductReviewResponse;
import com.online.shop.system.product.service.domain.ports.input.service.ProductReviewApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductReviewController {

    private final ProductReviewApplicationService productReviewApplicationService;

    @PostMapping("/reviews")
    public ResponseEntity<Data<ProductReviewResponse>> createProductReview(@RequestBody CreateProductReview createProductReview){
        return new ResponseEntity<>(new Data<>(productReviewApplicationService.createProductReview(createProductReview)), HttpStatus.CREATED);
    }

    @PatchMapping("/reviews")
    public ResponseEntity<Data<ProductReviewResponse>> updateProductReview(@RequestBody UpdateProductReview updateProductReview){
        return new ResponseEntity<>(new Data<>(productReviewApplicationService.updateProductReview(updateProductReview)), HttpStatus.OK);
    }

    @GetMapping("/reviews/{productReviewID}")
    public ResponseEntity<Data<GetProductReviewResponse>> getProductReview(@PathVariable("productReviewID") Integer productReviewID){
        return new ResponseEntity<>(new Data<>(productReviewApplicationService.getProductReview(productReviewID)), HttpStatus.OK);
    }

    @GetMapping("/{productID}/reviews")
    public ResponseEntity<PagingResponse> getProductReviewByProductID(@PathVariable("productID") UUID productID,
                                                                      @RequestParam(defaultValue = "1") int page,
                                                                      @RequestParam(defaultValue = "10") int size){
        return new ResponseEntity<>(productReviewApplicationService.getProductReviewByProductID(productID, page, size), HttpStatus.OK);
    }

    @GetMapping("/{productID}/reviews/{rating}")
    public ResponseEntity<PagingResponse> getProductReviewByProductIDAndRating(@PathVariable("productID") UUID productID,
                                                                               @PathVariable("rating") int rating,
                                                                      @RequestParam(defaultValue = "1") int page,
                                                                      @RequestParam(defaultValue = "10") int size){
        return new ResponseEntity<>(productReviewApplicationService.getProductReviewByProductIDAndRating(productID, rating, page, size), HttpStatus.OK);
    }

}
