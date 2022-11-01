package com.online.shop.system.product.review.content.service.domain.ports.input.service;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProductReviewContentApplicationService {

    void uploadContent(@Validated Integer productReviewID, UUID productID, List<MultipartFile> images, List<MultipartFile> videos);
}
