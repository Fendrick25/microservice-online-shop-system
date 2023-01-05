package com.online.shop.system.product.service.domain.ports.input.service;

import com.online.shop.system.product.service.domain.dto.create.response.CategoryIDResponse;
import com.online.shop.system.product.service.domain.dto.create.CreateCategory;
import com.online.shop.system.product.service.domain.dto.create.UpdateCategory;
import com.online.shop.system.product.service.domain.dto.create.response.GetCategoryResponse;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface CategoryApplicationService {
    CategoryIDResponse createCategory(@Valid CreateCategory createCategory);
    CategoryIDResponse updateCategory(@Valid UpdateCategory updateCategory);
    CategoryIDResponse deleteCategory(@Valid UUID categoryID);
    GetCategoryResponse getCategory(@Valid UUID categoryID);
    List<GetCategoryResponse> getAllCategory();
}
