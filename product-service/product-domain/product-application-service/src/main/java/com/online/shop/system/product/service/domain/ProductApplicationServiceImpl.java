package com.online.shop.system.product.service.domain;

import com.online.shop.system.product.service.domain.dto.create.response.*;
import com.online.shop.system.product.service.domain.entity.Product;
import com.online.shop.system.product.service.domain.ports.input.service.ProductApplicationService;
import com.online.shop.system.product.service.domain.dto.create.CreateProduct;
import com.online.shop.system.product.service.domain.dto.create.UpdateProduct;
import com.online.shop.system.product.service.domain.mapper.ProductDataMapper;
import com.online.shop.system.product.service.domain.ports.output.repository.ProductRepository;
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
public class ProductApplicationServiceImpl implements ProductApplicationService {

    private final ProductDataMapper productDataMapper;
    private final ProductRepository productRepository;
    private final ProductDomainService productDomainService;

    @Override
    public ProductIDResponse createProduct(CreateProduct createProduct) {
        Product product = productDomainService.validateProduct(productDataMapper.createProductToProduct(createProduct));
        log.info("Product with id: {} created", product.getProductID());
        return productDataMapper.productToProductIDResponse(productRepository.createProduct(product));
    }

    @Override
    public ProductIDResponse updateProduct(UpdateProduct updateProduct) {
        Product product = productDomainService.updateProduct(productDataMapper.updateProductToProduct(updateProduct));
        log.info("Product with id: {} updated", product.getProductID());
        return productDataMapper.productToProductIDResponse(productRepository.updateProduct(product));
    }

    @Override
    public ProductIDResponse deleteProduct(UUID productID) {
        productRepository.deleteProduct(productID);
        log.info("Product with id: {} deleted", productID);
        return ProductIDResponse.builder()
                .productID(productID)
                .build();
    }

    @Override
    public GetProductResponse getProduct(UUID productID) {
        Product product = productRepository.getProduct(productID);
        log.info("Product with id: {} found", product.getProductID());
        return productDataMapper.productToGetProductResponse(product);
    }

    @Override
    public List<GetProductResponse> getAllProduct() {
        return productRepository.getAllProduct().stream()
                .map(productDataMapper::productToGetProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PagingResponse getProductByCategory(UUID categoryID, int page, int size) {
        return productRepository.getProductByCategory(categoryID, page, size);
    }

    @Override
    public PagingResponse searchProduct(String productName, int page, int size) {
        return productRepository.searchProduct(productName, page, size);
    }


}
