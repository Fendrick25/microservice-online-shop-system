package com.online.shop.system.product.service.domain.ports.input.service;

import com.online.shop.system.product.service.domain.dto.create.CheckProductStock;
import com.online.shop.system.product.service.domain.dto.create.CreateProduct;
import com.online.shop.system.product.service.domain.dto.create.UpdateProduct;
import com.online.shop.system.product.service.domain.dto.create.response.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

public interface ProductApplicationService {
    ProductIDResponse createProduct(@Validated CreateProduct createProduct);
    ProductIDResponse updateProduct(@Validated UpdateProduct updateProduct);
    ProductIDResponse deleteProduct(@Validated UUID productID);
    GetProductResponse getProduct(@Validated UUID productID);
    List<GetProductResponse> getAllProduct();
    PagingResponse getProductByCategory(@Validated UUID categoryID, int page, int size);
    PagingResponse searchProduct(@Validated String productName, int page, int size);
    List<CheckProductStockResponse> checkProductStock(List<CheckProductStock> checkProductStocks);

    List<CartGetProductResponse> getProducts(List<UUID> productIDs);
}
