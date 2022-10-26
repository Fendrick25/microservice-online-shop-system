package com.online.shop.system.service.domain.ports.input.service;

import com.online.shop.system.service.domain.create.CreateProduct;
import com.online.shop.system.service.domain.create.UpdateProduct;
import com.online.shop.system.service.domain.create.response.GetProductResponse;
import com.online.shop.system.service.domain.create.response.PagingResponse;
import com.online.shop.system.service.domain.create.response.ProductIDResponse;
import com.online.shop.system.service.domain.create.response.ProductOverview;

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
