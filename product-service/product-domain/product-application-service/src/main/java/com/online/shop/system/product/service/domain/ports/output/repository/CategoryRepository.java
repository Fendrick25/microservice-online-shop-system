package com.online.shop.system.product.service.domain.ports.output.repository;

import com.online.shop.system.product.service.domain.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository {
    Category createCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(UUID categoryID);

    Category getCategory(UUID categoryID);
    List<Category> getAllCategory();
}
