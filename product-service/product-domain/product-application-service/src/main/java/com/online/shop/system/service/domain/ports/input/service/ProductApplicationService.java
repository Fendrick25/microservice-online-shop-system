package com.online.shop.system.service.domain.ports.input.service;

import com.online.shop.system.service.domain.create.CreateCategory;
import com.online.shop.system.service.domain.create.CreateProduct;
import com.online.shop.system.service.domain.create.response.CreateCategoryResponse;
import com.online.shop.system.service.domain.create.response.CreateProductResponse;

public interface ProductApplicationService {
    CreateProductResponse createProduct(CreateProduct createProduct);
    CreateCategoryResponse createCategory(CreateCategory createCategory);
}
