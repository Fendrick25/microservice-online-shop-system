package com.online.shop.system.product.service.dataaccess.mapper;

import com.online.shop.system.product.service.dataaccess.entity.CategoryEntity;
import com.online.shop.system.product.service.domain.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryDataAccessMapper {

    public CategoryEntity categoryToCategoryEntity(Category category){
        return CategoryEntity.builder()
                .categoryID(category.getCategoryID())
                .name(category.getName())
                .build();
    }

    public Category categoryEntityToCategory(CategoryEntity categoryEntity){
        return Category.builder()
                .categoryID(categoryEntity.getCategoryID())
                .name(categoryEntity.getName())
                .build();
    }
}
