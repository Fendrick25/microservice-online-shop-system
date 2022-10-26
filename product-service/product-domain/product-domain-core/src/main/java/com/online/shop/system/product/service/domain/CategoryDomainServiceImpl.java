package com.online.shop.system.product.service.domain;

import com.online.shop.system.product.service.domain.entity.Category;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategoryDomainServiceImpl implements CategoryDomainService {
    @Override
    public Category validateCategory(Category category) {
        category.initializeCategory();
        log.info("Category with id: {} is initiated", category.getCategoryID());
        return category;
    }
}
