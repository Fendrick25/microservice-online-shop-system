package com.online.shop.system.service.domain.mapper;

import com.online.shop.system.product.service.domain.entity.Product;
import com.online.shop.system.service.domain.create.CreateProduct;
import com.online.shop.system.service.domain.create.UpdateProduct;
import com.online.shop.system.service.domain.create.response.GetProductResponse;
import com.online.shop.system.service.domain.create.response.ProductIDResponse;
import com.online.shop.system.service.domain.create.response.ProductOverview;
import org.springframework.stereotype.Component;

@Component
public class ProductDataMapper {
    public Product createProductToProduct(CreateProduct createProduct){
        return Product.builder()
                .name(createProduct.getName())
                .description(createProduct.getDescription())
                .price(createProduct.getPrice())
                .quantity(createProduct.getQuantity())
                .category(createProduct.getCategory())
                .build();
    }

    public ProductIDResponse productToProductIDResponse(Product product){
        return ProductIDResponse.builder()
                .productID(product.getProductID())
                .build();
    }

    public Product updateProductToProduct(UpdateProduct updateProduct){
        return Product.builder()
                .productID(updateProduct.getProductID())
                .name(updateProduct.getName())
                .description(updateProduct.getDescription())
                .price(updateProduct.getPrice())
                .quantity(updateProduct.getQuantity())
                .build();
    }

    public GetProductResponse productToGetProductResponse(Product product){
        return GetProductResponse.builder()
                .productID(product.getProductID())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .rating(product.getRating())
                .productStatus(product.getProductStatus())
                .soldAmount(product.getSoldAmount())
                .category(product.getCategory())
                .imageUrl(product.getImageUrl())
                .build();
    }

    public ProductOverview productToProductOverview(Product product){
        return ProductOverview.builder()
                .productID(product.getProductID())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .rating(product.getRating())
                .soldAmount(product.getSoldAmount())
                .imageUrl(product.getImageUrl())
                .build();
    }
}
