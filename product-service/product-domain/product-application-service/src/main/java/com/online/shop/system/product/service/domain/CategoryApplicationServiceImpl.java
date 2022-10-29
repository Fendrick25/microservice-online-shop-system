package com.online.shop.system.product.service.domain;

import com.online.shop.system.product.service.domain.dto.create.response.CategoryIDResponse;
import com.online.shop.system.product.service.domain.dto.create.response.GetCategoryResponse;
import com.online.shop.system.product.service.domain.entity.Category;
import com.online.shop.system.product.service.domain.ports.input.service.CategoryApplicationService;
import com.online.shop.system.product.service.domain.ports.output.repository.CategoryRepository;
import com.online.shop.system.product.service.domain.dto.create.CreateCategory;
import com.online.shop.system.product.service.domain.dto.create.UpdateCategory;
import com.online.shop.system.product.service.domain.mapper.CategoryDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class CategoryApplicationServiceImpl implements CategoryApplicationService {

    private final CategoryDomainService categoryDomainService;
    private final CategoryRepository categoryRepository;
    private final CategoryDataMapper categoryDataMapper;

    @Override
    public CategoryIDResponse createCategory(CreateCategory createCategory) {
        Category category = categoryDomainService.validateCategory(Category.builder()
                .name(createCategory.getName()).build());
        log.info("Category with id: {} created", category.getCategoryID());
        return categoryDataMapper.categoryToCategoryIDResponse(categoryRepository.createCategory(category));
    }

    @Override
    public CategoryIDResponse updateCategory(UpdateCategory updateCategory) {
        Category category = categoryRepository.updateCategory(categoryDataMapper.updateCategoryToCategory(updateCategory));
        log.info("Category with id: {} updated", category.getCategoryID());
        return categoryDataMapper.categoryToCategoryIDResponse(category);
    }

    @Override
    public CategoryIDResponse deleteCategory(UUID categoryID) {
        categoryRepository.deleteCategory(categoryID);
        log.info("Category with id: {} deleted", categoryID);
        return CategoryIDResponse.builder()
                .categoryID(categoryID)
                .build();
    }

    @Override
    public GetCategoryResponse getCategory(UUID categoryID) {
        log.info("Category with id: {} found", categoryID);
        return categoryDataMapper.categoryToGetCategoryResponse(categoryRepository.getCategory(categoryID));
    }

    @Override
    public List<GetCategoryResponse> getAllCategory() {
        return categoryRepository.getAllCategory().stream()
                .map(categoryDataMapper::categoryToGetCategoryResponse)
                .collect(Collectors.toList());
    }
}
