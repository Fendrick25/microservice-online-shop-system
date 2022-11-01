package com.online.shop.system.product.review.content.service.domain;

import com.online.shop.system.product.review.content.service.domain.dto.ProductReviewContentUrl;
import com.online.shop.system.product.review.content.service.domain.entity.Content;
import com.online.shop.system.product.review.content.service.domain.entity.ProductReviewContent;
import com.online.shop.system.product.review.content.service.domain.ports.input.service.ProductReviewContentApplicationService;
import com.online.shop.system.product.review.content.service.domain.ports.output.message.publisher.ProductReviewContentUrlMessagePublisher;
import com.online.shop.system.product.review.content.service.domain.ports.output.repository.ProductReviewContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Validated
@Slf4j
public class ProductReviewContentApplicationServiceImpl implements ProductReviewContentApplicationService {

    private final ProductReviewContentRepository productReviewContentRepository;
    private final ProductReviewContentUrlMessagePublisher productReviewContentUrlMessagePublisher;

    private final ProductReviewContentDomainService productReviewContentDomainService;

    @Override
    public void uploadContent(Integer productReviewID, UUID productID, List<MultipartFile> images, List<MultipartFile> videos) {
        ProductReviewContent productReviewContent = productReviewContentDomainService.validateProductReviewContent(ProductReviewContent.builder()
                        .productReviewID(productReviewID)
                        .productID(productID.toString())
                .build());
        List<Content> contents = new ArrayList<>();
        images.forEach(image -> {
            try {
                Content content = Content.builder()
                        .name(image.getOriginalFilename())
                        .size(String.valueOf(image.getSize()))
                        .type(image.getContentType())
                        .file(image.getBytes())
                        .build();

                contents.add(content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        productReviewContent.setFile(contents);
        String id = productReviewContentRepository.uploadContent(productReviewContent);
        String imageUrl = "http://localhost:8180/api/v1/products/reviews/contents/" + id;
        String videoUrl = "http://cloudservice.com/api/v1/products/reviews/contents/"+ id;
        productReviewContentUrlMessagePublisher.publish(ProductReviewContentUrl.builder()
                        .productReviewID(productReviewContent.getProductReviewID())
                        .productID(productID)
                        .imageUrl(imageUrl)
                        .videoUrl(videoUrl)
                .build());
    }
}
