package com.online.shop.system.service.domain.mapper;

import com.online.shop.system.product.service.domain.entity.Category;
import com.online.shop.system.service.domain.create.response.CreateCategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryDataMapper {

    public CreateCategoryResponse categoryToCreateCategoryResponse(Category category){
        return CreateCategoryResponse.builder()
                .categoryID(category.getCategoryID())
                .build();
    }
}
