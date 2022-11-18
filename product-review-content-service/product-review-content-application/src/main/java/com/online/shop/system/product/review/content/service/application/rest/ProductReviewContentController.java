package com.online.shop.system.product.review.content.service.application.rest;

import com.online.shop.system.product.review.content.service.domain.ports.input.service.ProductReviewContentApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products/reviews/contents")
public class ProductReviewContentController {

    private final ProductReviewContentApplicationService productReviewContentApplicationService;

    @PostMapping("/{productID}")
    public ResponseEntity<?> uploadProductReviewContent(@PathVariable("productID")UUID productID,
                                                        @RequestParam("productReviewID") Integer productReviewID,
                                                        @RequestParam("images")MultipartFile[] images,
                                                        @RequestParam("videos")MultipartFile[] videos){
        productReviewContentApplicationService.uploadContent(productReviewID, productID, List.of(images), List.of(videos));
        return ResponseEntity.ok("");
    }


}
