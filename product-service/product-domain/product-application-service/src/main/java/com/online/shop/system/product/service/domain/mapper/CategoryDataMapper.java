package com.online.shop.system.product.service.domain.mapper;

import com.online.shop.system.product.service.domain.entity.Category;
import com.online.shop.system.product.service.domain.dto.create.UpdateCategory;
import com.online.shop.system.product.service.domain.dto.create.response.CategoryIDResponse;
import com.online.shop.system.product.service.domain.dto.create.response.GetCategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryDataMapper {

    public CategoryIDResponse categoryToCreateCategoryIDResponse(Category category){
        return CategoryIDResponse.builder()
                .categoryID(category.getCategoryID())
                .build();
    }

    public Category updateCategoryToCategory(UpdateCategory updateCategory){
        return Category.builder()
                .categoryID(updateCategory.getCategoryID())
                .name(updateCategory.getName())
                .build();
    }

    public GetCategoryResponse categoryToGetCategoryResponse(Category category){
        return GetCategoryResponse.builder()
                .categoryID(category.getCategoryID())
                .name(category.getName())
                .build();
    }

}
