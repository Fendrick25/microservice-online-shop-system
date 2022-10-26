package com.online.shop.system.service.domain.ports.input.service;

import com.online.shop.system.product.service.domain.entity.Category;
import com.online.shop.system.service.domain.create.CreateCategory;
import com.online.shop.system.service.domain.create.UpdateCategory;
import com.online.shop.system.service.domain.create.response.CategoryIDResponse;
import com.online.shop.system.service.domain.create.response.GetCategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryApplicationService {
    CategoryIDResponse createCategory(CreateCategory createCategory);
    CategoryIDResponse updateCategory(UpdateCategory updateCategory);
    CategoryIDResponse deleteCategory(UUID categoryID);
    GetCategoryResponse getCategory(UUID categoryID);
    List<GetCategoryResponse> getAllCategory();
}
