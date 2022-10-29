package com.online.shop.system.product.service.dataaccess.adapter;

import com.online.shop.system.product.service.dataaccess.entity.CategoryEntity;
import com.online.shop.system.product.service.dataaccess.exception.ResourceNotFoundException;
import com.online.shop.system.product.service.dataaccess.mapper.CategoryDataAccessMapper;
import com.online.shop.system.product.service.dataaccess.repository.CategoryJpaRepository;
import com.online.shop.system.product.service.domain.entity.Category;
import com.online.shop.system.product.service.domain.ports.output.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryJpaRepository categoryJpaRepository;
    private final CategoryDataAccessMapper categoryDataAccessMapper;
    @Override
    public Category createCategory(Category category) {
        return categoryDataAccessMapper.categoryEntityToCategory(categoryJpaRepository.save(categoryDataAccessMapper.categoryToCategoryEntity(category)));
    }

    @Override
    @Transactional
    public Category updateCategory(Category category) {
        CategoryEntity categoryEntity = findCategory(category.getCategoryID());
        categoryEntity.setName(category.getName());
        return categoryDataAccessMapper.categoryEntityToCategory(categoryJpaRepository.save(categoryEntity));
    }

    @Override
    public void deleteCategory(UUID categoryID) {
        findCategory(categoryID);
        categoryJpaRepository.deleteById(categoryID);
    }

    @Override
    public Category getCategory(UUID categoryID) {
        return categoryDataAccessMapper.categoryEntityToCategory(findCategory(categoryID));
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryJpaRepository.findAll().stream().map(categoryDataAccessMapper::categoryEntityToCategory).collect(Collectors.toList());
    }

    private CategoryEntity findCategory(UUID categoryID){
        return categoryJpaRepository.findById(categoryID).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }
}
