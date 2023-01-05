package com.online.shop.system.product.service.domain.ports.input.service;

import com.online.shop.system.product.service.domain.dto.create.CheckProductStock;
import com.online.shop.system.product.service.domain.dto.create.CreateProduct;
import com.online.shop.system.product.service.domain.dto.create.UpdateProduct;
import com.online.shop.system.product.service.domain.dto.create.response.*;
import com.online.shop.system.product.service.domain.dto.message.UpdateProductStock;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface ProductApplicationService {
    ProductIDResponse createProduct(@Valid CreateProduct createProduct);
    ProductIDResponse updateProduct(@Valid UpdateProduct updateProduct);
    ProductIDResponse deleteProduct(@Valid UUID productID);
    GetProductResponse getProduct(@Valid UUID productID);
    List<GetProductResponse> getAllProduct();
    PagingResponse getProductByCategory(@Valid UUID categoryID, int page, int size);
    PagingResponse searchProduct(@Valid String productName, int page, int size);
    List<CheckProductStockResponse> checkProductStock(List<CheckProductStock> checkProductStocks);
    List<CartGetProductResponse> getProducts(List<UUID> productIDs);
    void updateProductStock(@Valid UpdateProductStock updateProductStock);
}
