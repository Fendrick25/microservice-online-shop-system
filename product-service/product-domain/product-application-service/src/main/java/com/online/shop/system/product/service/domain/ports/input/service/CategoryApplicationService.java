package com.online.shop.system.product.service.domain.ports.input.service;

import com.online.shop.system.product.service.domain.dto.create.response.CategoryIDResponse;
import com.online.shop.system.product.service.domain.dto.create.CreateCategory;
import com.online.shop.system.product.service.domain.dto.create.UpdateCategory;
import com.online.shop.system.product.service.domain.dto.create.response.GetCategoryResponse;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

public interface CategoryApplicationService {
    CategoryIDResponse createCategory(@Validated CreateCategory createCategory);
    CategoryIDResponse updateCategory(@Validated UpdateCategory updateCategory);
    CategoryIDResponse deleteCategory(@Validated UUID categoryID);
    GetCategoryResponse getCategory(@Validated UUID categoryID);
    List<GetCategoryResponse> getAllCategory();
}
