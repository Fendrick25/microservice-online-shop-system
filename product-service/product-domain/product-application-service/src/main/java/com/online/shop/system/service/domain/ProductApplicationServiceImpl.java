package com.online.shop.system.service.domain;

import com.online.shop.system.product.service.domain.ProductDomainService;
import com.online.shop.system.product.service.domain.entity.Category;
import com.online.shop.system.product.service.domain.entity.Product;
import com.online.shop.system.service.domain.create.CreateCategory;
import com.online.shop.system.service.domain.create.CreateProduct;
import com.online.shop.system.service.domain.create.response.CreateCategoryResponse;
import com.online.shop.system.service.domain.create.response.CreateProductResponse;
import com.online.shop.system.service.domain.mapper.CategoryDataMapper;
import com.online.shop.system.service.domain.mapper.ProductDataMapper;
import com.online.shop.system.service.domain.ports.input.service.ProductApplicationService;
import com.online.shop.system.service.domain.ports.output.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class ProductApplicationServiceImpl implements ProductApplicationService {

    private final ProductDataMapper productDataMapper;
    private final CategoryDataMapper categoryDataMapper;
    private final ProductRepository productRepository;
    private final ProductDomainService productDomainService;

    @Override
    public CreateProductResponse createProduct(CreateProduct createProduct) {
        Product product = productDomainService.validateProduct(productDataMapper.createProductToProduct(createProduct));
        productRepository.createProduct(product);
        return productDataMapper.productToCreateProductResponse(product);
    }

    @Override
    public CreateCategoryResponse createCategory(CreateCategory createCategory) {
        Category category = productDomainService.validateCategory(Category.builder()
                        .categoryID(createCategory.getCategoryID())
                        .name(createCategory.getName()).build());
        productRepository.createCategory(category);
        return categoryDataMapper.categoryToCreateCategoryResponse(category);
    }
}
