package com.online.shop.system.product.service.domain.ports.input.service;

import com.online.shop.system.product.service.domain.dto.create.CreateProduct;
import com.online.shop.system.product.service.domain.dto.create.UpdateProduct;
import com.online.shop.system.product.service.domain.dto.create.response.GetProductResponse;
import com.online.shop.system.product.service.domain.dto.create.response.PagingResponse;
import com.online.shop.system.product.service.domain.dto.create.response.ProductIDResponse;

import java.util.List;
import java.util.UUID;

public interface ProductApplicationService {
    ProductIDResponse createProduct(CreateProduct createProduct);
    ProductIDResponse updateProduct(UpdateProduct updateProduct);
    ProductIDResponse deleteProduct(UUID productID);
    GetProductResponse getProduct(UUID productID);
    List<GetProductResponse> getAllProduct();
    PagingResponse getProductByCategory(UUID categoryID, int page, int size);
    PagingResponse searchProduct(String productName, int page, int size);
}
